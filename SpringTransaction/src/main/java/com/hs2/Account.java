package com.hs2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Account {

	@Id
	@Column(name="accountno")
	private int accno;

	@Column(name="balance")
	private double bal;

	public Account() {

	}

	public Account(int accno, String atype, double bal) {
		super();
		this.accno = accno;
		this.bal = bal;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public double getBal() {
		return bal;
	}

	public void setBal(double bal) {
		this.bal = bal;
	}
}