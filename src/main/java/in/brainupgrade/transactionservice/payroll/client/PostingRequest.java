package in.brainupgrade.transactionservice.payroll.client;

import java.time.LocalDate;

/**
 * Mirrors the request body of POST /api/v1/postings on global-bank-account.
 * Kept by hand. If the service contract changes, this changes with it.
 */
public class PostingRequest {

    private String clientReference;
    private String debitAccountId;
    private String creditAccountId;
    private long amountMinor;
    private LocalDate valueDate;
    private String narrative;

    public PostingRequest(String clientReference, String debitAccountId, String creditAccountId,
                          long amountMinor, LocalDate valueDate, String narrative) {
        this.clientReference = clientReference;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
        this.amountMinor = amountMinor;
        this.valueDate = valueDate;
        this.narrative = narrative;
    }

    public String getClientReference() {
        return clientReference;
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public long getAmountMinor() {
        return amountMinor;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public String getNarrative() {
        return narrative;
    }
}
