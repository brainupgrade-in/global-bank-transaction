package in.brainupgrade.transactionservice.payroll.service;

import in.brainupgrade.transactionservice.payroll.client.PostingClient;
import in.brainupgrade.transactionservice.payroll.client.PostingClientException;
import in.brainupgrade.transactionservice.payroll.client.PostingRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Turns a payroll batch into one posting per employee against the funding account.
 */
@Service
public class PayrollDisbursementService {

    private static final Logger log = LoggerFactory.getLogger(PayrollDisbursementService.class);

    private final PostingClient postingClient;
    private final String fundingAccountId;

    public PayrollDisbursementService(PostingClient postingClient,
                                      @Value("${posting.funding-account-id}") String fundingAccountId) {
        this.postingClient = postingClient;
        this.fundingAccountId = fundingAccountId;
    }

    public DisbursementResult disburse(String batchId, List<PayrollItem> items, LocalDate valueDate) {
        int accepted = 0;
        List<String> rejected = new ArrayList<>();

        LocalDate settlementDate = postingClient.resolveSettlementDate(valueDate);

        for (PayrollItem item : items) {
            String clientReference = batchId + "-" + item.getReference();
            PostingRequest request = new PostingRequest(
                    clientReference,
                    fundingAccountId,
                    item.getEmployeeAccountId(),
                    item.getAmountMinor(),
                    valueDate,
                    settlementDate,
                    "Payroll " + batchId);
            try {
                postingClient.post(request);
                accepted++;
            } catch (PostingClientException ex) {
                log.warn("Payroll item {} rejected", clientReference, ex);
                rejected.add(clientReference);
            }
        }

        return new DisbursementResult(batchId, accepted, rejected);
    }
}
