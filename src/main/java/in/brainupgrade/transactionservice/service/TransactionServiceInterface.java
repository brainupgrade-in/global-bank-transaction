package in.brainupgrade.transactionservice.service;

import in.brainupgrade.transactionservice.util.AccountInput;
import in.brainupgrade.transactionservice.util.TransactionInput;

public interface TransactionServiceInterface {

	public boolean makeTransfer(String token, TransactionInput transactionInput);

	public boolean makeWithdraw(String token, AccountInput accountInput);

	public boolean makeDeposit(String token, AccountInput accountInput);
	
	public boolean makeServiceCharges(String token, AccountInput accountInput);
}
