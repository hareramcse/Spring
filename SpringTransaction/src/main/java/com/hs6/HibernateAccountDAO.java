package com.hs6;

import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HibernateAccountDAO implements AccoutnDAO {

	@Autowired
	HibernateTemplate hibernateTemplate = null;

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void deposit(int accno, double amt) {
		Account acc = hibernateTemplate.load(Account.class, accno, LockMode.READ);
		acc.setBal(acc.getBal() + amt);
		hibernateTemplate.update(acc);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void withdraw(int accno, double amt) {
		try {
			Account acc = hibernateTemplate.load(Account.class, accno, LockMode.READ);
			double cbal = acc.getBal();
			if (cbal >= 5000 + amt) {
				acc.setBal(cbal - amt);
				hibernateTemplate.update(acc);
			} else {
				throw new InSufficientFundsException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void fundsTransfer(int saccno, int daccno, double amt) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public double getBalance(int accno) {
		Account acc = hibernateTemplate.load(Account.class, accno, LockMode.READ);
		double cbal = acc.getBal();
		return cbal;
	}
}