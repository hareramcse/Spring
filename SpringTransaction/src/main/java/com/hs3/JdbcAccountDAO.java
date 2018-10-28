package com.hs3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcAccountDAO implements AccoutnDAO {

	@Autowired
	SimpleJdbcTemplate simpleJdbcTemplate = null;

	public void deposit(int accno, double amt) {
		String sql = "select balance from account where accountno=?";
		int x = simpleJdbcTemplate.queryForInt(sql, accno);
		double cbal = new Integer(x).doubleValue();
		double nbal = cbal + amt;
		String sql1 = "update account set balance=? where accountno=?";
		simpleJdbcTemplate.update(sql1, nbal, accno);
	}

	public void withdraw(int accno, double amt) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fundsTransfer(int saccno, int daccno, double amt) {
		try {
			String sql1 = "select balance from account where accountno=?";
			String sql2 = "update account set balance=? where accountno=?";
			int y = simpleJdbcTemplate.queryForInt(sql1, daccno);
			double dcbal = new Integer(y).doubleValue();
			System.out.println("Before Deposit " + dcbal);
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
		} catch (Exception e) {
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
