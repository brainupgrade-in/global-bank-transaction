package in.brainupgrade.transactionservice.payroll.api;

import in.brainupgrade.transactionservice.payroll.service.DisbursementResult;
import in.brainupgrade.transactionservice.payroll.service.PayrollDisbursementService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DisbursementController {

    private final PayrollDisbursementService disbursementService;

    public DisbursementController(PayrollDisbursementService disbursementService) {
        this.disbursementService = disbursementService;
    }

    @PostMapping("/disbursements")
    public DisbursementResult disburse(@Valid @RequestBody DisbursementRequest request) {
        LocalDate valueDate = request.getValueDate() != null ? request.getValueDate() : LocalDate.now();
        return disbursementService.disburse(request.getBatchId(), request.getItems(), valueDate);
    }
}
