package in.brainupgrade.transactionservice.payroll.service;

import java.util.List;

public class DisbursementResult {

    private final String batchId;
    private final int accepted;
    private final List<String> rejectedReferences;

    public DisbursementResult(String batchId, int accepted, List<String> rejectedReferences) {
        this.batchId = batchId;
        this.accepted = accepted;
        this.rejectedReferences = rejectedReferences;
    }

    public String getBatchId() {
        return batchId;
    }

    public int getAccepted() {
        return accepted;
    }

    public List<String> getRejectedReferences() {
        return rejectedReferences;
    }
}
