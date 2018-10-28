package com.hs2;

import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class AccountDAOImpl implements AccoutnDAO {

	@Autowired
	HibernateTemplate hibernateTemplate = null;

	@Autowired
	HibernateTransactionManager txManager = null;

	public void deposit(int accno, double amt) {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		TransactionStatus ts = txManager.getTransaction(txDefinition);

		Account acc = hibernateTemplate.load(Account.class, accno, LockMode.READ);
		acc.setBal(acc.getBal() + amt);
		hibernateTemplate.update(acc);
		txManager.commit(ts);
	}

	public void withdraw(int accno, double amt) {
		TransactionDefinition txDefinition = new DefaultTransactionDefinition();
		TransactionStatus ts = txManager.getTransaction(txDefinition);
		try {
			Account acc = hibernateTemplate.load(Account.class, accno, LockMode.READ);
			double cbal = acc.getBal();
			if (cbal >= 5000 + amt) {
				acc.setBal(cbal - amt);
				hibernateTemplate.update(acc);
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
			Account acc1 = hibernateTemplate.load(Account.class, daccno, LockMode.READ);
			acc1.setBal(acc1.getBal() + amt);
			hibernateTemplate.update(acc1);

			Account acc2 = hibernateTemplate.load(Account.class, saccno, LockMode.READ);
			double scbal = acc2.getBal();
			if (scbal >= 5000 + amt) {
				acc2.setBal(scbal - amt);
				hibernateTemplate.update(acc2);
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
		Account acc = hibernateTemplate.load(Account.class, accno, LockMode.READ);
		double cbal = acc.getBal();
		return cbal;
	}
}