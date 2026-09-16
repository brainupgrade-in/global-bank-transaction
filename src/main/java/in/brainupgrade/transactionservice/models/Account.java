package in.brainupgrade.transactionservice.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

public class Account {
	private long accountId;
	private String customerId;
	private double currentBalance;
	private String accountType;
	private String ownerName;
	@Transient
	private transient List<Transaction> transactions;

	public long getAccountId() {
		return this.accountId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public double getCurrentBalance() {
		return this.currentBalance;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	public void setCurrentBalance(final double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setAccountType(final String accountType) {
		this.accountType = accountType;
	}

	public void setOwnerName(final String ownerName) {
		this.ownerName = ownerName;
	}

	public void setTransactions(final List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Account(final long accountId, final String customerId, final double currentBalance, final String accountType, final String ownerName, final List<Transaction> transactions) {
		this.accountId = accountId;
		this.customerId = customerId;
		this.currentBalance = currentBalance;
		this.accountType = accountType;
		this.ownerName = ownerName;
		this.transactions = transactions;
	}

	public Account() {
	}
}
