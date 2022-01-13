package in.brainupgrade.transactionservice.models;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}