package com.hs1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class AccountDAOImpl implements AccoutnDAO {

	@Autowired
	SimpleJdbcTemplate simpleJdbcTemplate = null;

	@Autowired
	DataSourceTransactionManager txManager = null;

	public void deposit(int accno, double amt) {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		TransactionStatus ts = txManager.getTransaction(txDefinition);
		String sql = "select balance from account where accountno=?";
		int x = simpleJdbcTemplate.queryForInt(sql, accno);
		double cbal = new Integer(x).doubleValue();
		double nbal = cbal + amt;
		String sql1 = "update account set balance=? where accountno=?";
		simpleJdbcTemplate.update(sql1, nbal, accno);
		txManager.commit(ts);
	}

	public void withdraw(int accno, double amt) {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		TransactionStatus ts = txManager.getTransaction(txDefinition);
		try {
			String sql = "select balance from account where accountno=?";
			int x = simpleJdbcTemplate.queryForInt(sql, accno);
			double cbal = new Integer(x).doubleValue();
			if (cbal >= 5000 + amt) {
				double nbal = cbal - amt;
				String sql1 = "update account set balance =? where accountno=?";
				simpleJdbcTemplate.update(sql1, nbal, accno);
			} else {
				throw new InSufficientFundsException();
			}
			txManager.commit(ts);
		} catch (Exception e) {
			txManager.rollback(ts);
			e.printStackTrace();
		}
	}

	public void fundsTransfer(int saccno, int daccno, double amt) {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		TransactionStatus ts = txManager.getTransaction(txDefinition);
		try {
			String sql1 = "select balance from account where accountno=?";
			int y = simpleJdbcTemplate.queryForInt(sql1, daccno);
			double dcbal = new Integer(y).doubleValue();
			System.out.println("Before Deposit " + dcbal);
			String sql2 = "update account set balance=? where accountno=?";
			double dnbal = dcbal + amt;
			simpleJdbcTemplate.update(sql2, dnbal, daccno);
			dcbal = new Integer(y).doubleValue();
			System.out.println("after Deposit " + dcbal);
			int x = simpleJdbcTemplate.queryForInt(sql1, saccno);
			double scbal = new Integer(x).doubleValue();
			if (scbal >= 3000 + amt) {
				double snbal = scbal - amt;
				simpleJdbcTemplate.update(sql2, snbal, saccno);
			} else {
				throw new InSufficientFundsException();
			}
			txManager.commit(ts);
		} catch (Exception e) {
			txManager.rollback(ts);
			e.printStackTrace();
		}
	}

	public double getBalance(int accno) {
		String sql = "select balance from account where accountno=?";
		int x = simpleJdbcTemplate.queryForInt(sql, accno);
		double cbal = new Integer(x).doubleValue();
		return cbal;
	}

}
