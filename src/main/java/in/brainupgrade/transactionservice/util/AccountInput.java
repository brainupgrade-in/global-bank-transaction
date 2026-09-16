package in.brainupgrade.transactionservice.util;

import jakarta.validation.constraints.NotNull;

public class AccountInput {
	@NotNull(message = "Account number is mandatory")
	private long accountId;
	@NotNull(message = "Amount is mandatory")
	private double amount;

	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public long getAccountId() {
		return this.accountId;
	}

	public double getAmount() {
		return this.amount;
	}

	public AccountInput() {
	}

	public AccountInput(final long accountId, final double amount) {
		this.accountId = accountId;
		this.amount = amount;
	}
}
