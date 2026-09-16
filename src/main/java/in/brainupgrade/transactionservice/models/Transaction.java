package in.brainupgrade.transactionservice.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long sourceAccountId;
	private String sourceOwnerName;
	private long targetAccountId;
	private String targetOwnerName;
	private double amount;
	private LocalDateTime initiationDate;
	private String reference;

	public long getId() {
		return this.id;
	}

	public long getSourceAccountId() {
		return this.sourceAccountId;
	}

	public String getSourceOwnerName() {
		return this.sourceOwnerName;
	}

	public long getTargetAccountId() {
		return this.targetAccountId;
	}

	public String getTargetOwnerName() {
		return this.targetOwnerName;
	}

	public double getAmount() {
		return this.amount;
	}

	public LocalDateTime getInitiationDate() {
		return this.initiationDate;
	}

	public String getReference() {
		return this.reference;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setSourceAccountId(final long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public void setSourceOwnerName(final String sourceOwnerName) {
		this.sourceOwnerName = sourceOwnerName;
	}

	public void setTargetAccountId(final long targetAccountId) {
		this.targetAccountId = targetAccountId;
	}

	public void setTargetOwnerName(final String targetOwnerName) {
		this.targetOwnerName = targetOwnerName;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public void setInitiationDate(final LocalDateTime initiationDate) {
		this.initiationDate = initiationDate;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

	public Transaction(final long id, final long sourceAccountId, final String sourceOwnerName, final long targetAccountId, final String targetOwnerName, final double amount, final LocalDateTime initiationDate, final String reference) {
		this.id = id;
		this.sourceAccountId = sourceAccountId;
		this.sourceOwnerName = sourceOwnerName;
		this.targetAccountId = targetAccountId;
		this.targetOwnerName = targetOwnerName;
		this.amount = amount;
		this.initiationDate = initiationDate;
		this.reference = reference;
	}

	public Transaction() {
	}
}
