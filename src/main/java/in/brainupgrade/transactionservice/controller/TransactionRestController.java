package in.brainupgrade.transactionservice.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import in.brainupgrade.transactionservice.feign.AccountFeign;
import in.brainupgrade.transactionservice.feign.RulesFeign;
import in.brainupgrade.transactionservice.models.Transaction;
import in.brainupgrade.transactionservice.repository.TransactionRepository;
import in.brainupgrade.transactionservice.service.TransactionServiceInterface;
import in.brainupgrade.transactionservice.util.AccountInput;
import in.brainupgrade.transactionservice.util.TransactionInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@CrossOrigin(origins = "*")
public class TransactionRestController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TransactionRestController.class);
	public static final String METHOD_FOR_MAKETRANSFER = "AccountFallbackForTransfer";
	public static final String METHOD_FOR_MAKEWITHDRAW = "AccountFallbackForWithdraw";
	public static final String METHOD_FOR_MAKEDEPOSIT = "AccountFallbackForDeposit";
	@Autowired
	AccountFeign accountFeign;
	@Autowired
	RulesFeign rulesFeign;
	@Autowired
	TransactionRepository transRepo;
	@Autowired
	TransactionServiceInterface transactionService;

	/**
	 * MakeTransfer method Transfers amount from One account to another
	 */
	@PostMapping("/transactions")
	@Operation(summary = "Transfer amount ", description = "Transfers amount from source acc to target acc ")
	public boolean makeTransfer(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "details required for transfer", required = true) @Valid @RequestBody TransactionInput transactionInput) {
		log.info("inside transaction method");
		log.info(transactionInput.toString());
		if (transactionInput != null) {
			boolean isComplete = transactionService.makeTransfer(token, transactionInput);
			return isComplete;
		} else {
			return false;
		}
	}

	/*
	 * Fallback method for transfer
	 */
	public boolean accountFallbackForTransfer() {
		log.error("Account Microservice is DOWN!");
		return false;
	}

	/**
	 * To get All transaction done in one account
	 * 
	 * @param token
	 * @param accId
	 * @return
	 */
	@Operation(summary = "Get all transactions", description = "Provide the id of the customer whose all transactions is to be retrieved")
	@GetMapping("/getAllTransByAccId/{id}")
	public List<Transaction> getTransactionsByAccId(@Parameter(description = "token passed in header for authentication", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "id of customer ", required = true) @PathVariable("id") long accId) {
		List<Transaction> slist = transRepo.findBySourceAccountIdOrTargetAccountIdOrderByInitiationDate(accId, accId);
		return slist;
	}

	/**
	 * Method for making a withdraw in one account
	 * 
	 * @param token
	 * @param accountInput
	 * @return
	 */
	@PostMapping("/withdraw")
	@Operation(summary = "Withdraw Amount ", description = "To withdraw cash from the account")
	public boolean makeWithdraw(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "Account Details", required = true) @Valid @RequestBody AccountInput accountInput) {
		transactionService.makeWithdraw(token, accountInput);
		return true;
	}

	@PostMapping("/servicecharge")
	@Operation(summary = "Service Charge ", description = "Service Charge to be cut for not maintaining the minimum balance ")
	public boolean makeServiceCharges(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "Account Details", required = true) @Valid @RequestBody AccountInput accountInput) {
		transactionService.makeServiceCharges(token, accountInput);
		return true;
	}

	/*
	 * Fallback method for Withdraw
	 */
	public boolean accountFallbackForWithdraw() {
		log.error("Rules Microservice is DOWN!");
		return false;
	}

	/**
	 * Method for making a deposit in a account
	 * 
	 * @param token
	 * @param accountInput
	 * @return
	 */
	@PostMapping("/deposit")
	@Operation(summary = "Deposit Amount ", description = "To deposit cash in the account")
	public ResponseEntity<?> makeDeposit(@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token, @Parameter(description = "Account details", required = true) @Valid @RequestBody AccountInput accountInput) {
		transactionService.makeDeposit(token, accountInput);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	/*
	 * Fallback method for deposit
	 */
	public ResponseEntity<?> accountFallbackForDeposit() {
		log.error("Rules Microservice is DOWN!");
		return new ResponseEntity<>(false, HttpStatus.GATEWAY_TIMEOUT);
	}
}
