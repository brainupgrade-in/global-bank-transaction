package in.brainupgrade.transactionservice.payroll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import in.brainupgrade.transactionservice.payroll.client.PostingClient;
import in.brainupgrade.transactionservice.payroll.client.PostingClientException;
import in.brainupgrade.transactionservice.payroll.client.PostingRequest;
import in.brainupgrade.transactionservice.payroll.client.PostingResponse;
import in.brainupgrade.transactionservice.payroll.service.DisbursementResult;
import in.brainupgrade.transactionservice.payroll.service.PayrollDisbursementService;
import in.brainupgrade.transactionservice.payroll.service.PayrollItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class PayrollDisbursementServiceTest {

    private final PostingClient postingClient = mock(PostingClient.class);
    private final PayrollDisbursementService service =
            new PayrollDisbursementService(postingClient, "ACC-PAYROLL");

    private PayrollItem item(String reference, String accountId, long amountMinor) {
        PayrollItem payrollItem = new PayrollItem();
        payrollItem.setReference(reference);
        payrollItem.setEmployeeAccountId(accountId);
        payrollItem.setAmountMinor(amountMinor);
        return payrollItem;
    }

    @Test
    void postsOnePostingPerItem() {
        when(postingClient.post(any(PostingRequest.class))).thenReturn(new PostingResponse());

        DisbursementResult result = service.disburse("BATCH-9",
                List.of(item("E1", "ACC-CLIENT-001", 1000L), item("E2", "ACC-CLIENT-002", 2000L)),
                LocalDate.of(2026, 3, 25));

        assertThat(result.getAccepted()).isEqualTo(2);
        assertThat(result.getRejectedReferences()).isEmpty();
    }

    @Test
    void recordsRejectedItemsWithoutFailingTheBatch() {
        doThrow(new PostingClientException("rejected", new RuntimeException()))
                .when(postingClient).post(any(PostingRequest.class));

        DisbursementResult result = service.disburse("BATCH-9",
                List.of(item("E1", "ACC-NOPE", 1000L)),
                LocalDate.of(2026, 3, 25));

        assertThat(result.getAccepted()).isZero();
        assertThat(result.getRejectedReferences()).containsExactly("BATCH-9-E1");
    }

    @Test
    void sendsTheSettlementDateOnEachPosting() {
        when(postingClient.resolveSettlementDate(any())).thenReturn(LocalDate.of(2026, 3, 27));
        when(postingClient.post(any(PostingRequest.class))).thenReturn(new PostingResponse());

        service.disburse("BATCH-9", List.of(item("E1", "ACC-CLIENT-001", 1000L)),
                LocalDate.of(2026, 3, 25));

        verify(postingClient).post(argThat(r -> LocalDate.of(2026, 3, 27).equals(r.getSettlementDate())));
    }
}
