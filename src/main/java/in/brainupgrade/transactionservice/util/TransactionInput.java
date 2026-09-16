package in.brainupgrade.transactionservice.util;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class TransactionInput {
	private AccountInput sourceAccount;
	private AccountInput targetAccount;
	@Positive(message = "Transfer amount must be positive")
	@Min(value = 1, message = "Amount must be larger than 1")
	private double amount;
	private String reference;

	public void setSourceAccount(final AccountInput sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public void setTargetAccount(final AccountInput targetAccount) {
		this.targetAccount = targetAccount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

	public AccountInput getSourceAccount() {
		return this.sourceAccount;
	}

	public AccountInput getTargetAccount() {
		return this.targetAccount;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getReference() {
		return this.reference;
	}

	public TransactionInput(final AccountInput sourceAccount, final AccountInput targetAccount, final double amount, final String reference) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.reference = reference;
	}

	public TransactionInput() {
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "TransactionInput(sourceAccount=" + this.getSourceAccount() + ", targetAccount=" + this.getTargetAccount() + ", amount=" + this.getAmount() + ", reference=" + this.getReference() + ")";
	}
}
