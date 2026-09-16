package in.brainupgrade.transactionservice.models;

import jakarta.validation.constraints.NotNull;

public class AccountInput {
	@NotNull
	private long accountId;
	@NotNull
	private double currentBalance;
	@NotNull
	private double amount;

	public void setAccountId(@NotNull final long accountId) {
		this.accountId = accountId;
	}

	public void setCurrentBalance(@NotNull final double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setAmount(@NotNull final double amount) {
		this.amount = amount;
	}

	@NotNull
	public long getAccountId() {
		return this.accountId;
	}

	@NotNull
	public double getCurrentBalance() {
		return this.currentBalance;
	}

	@NotNull
	public double getAmount() {
		return this.amount;
	}

	public AccountInput() {
	}

	public AccountInput(@NotNull final long accountId, @NotNull final double currentBalance, @NotNull final double amount) {
		this.accountId = accountId;
		this.currentBalance = currentBalance;
		this.amount = amount;
	}
}
