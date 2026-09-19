package in.brainupgrade.transactionservice.payroll.service;

public class PayrollItem {

    private String employeeAccountId;
    private long amountMinor;
    private String reference;

    public String getEmployeeAccountId() {
        return employeeAccountId;
    }

    public void setEmployeeAccountId(String employeeAccountId) {
        this.employeeAccountId = employeeAccountId;
    }

    public long getAmountMinor() {
        return amountMinor;
    }

    public void setAmountMinor(long amountMinor) {
        this.amountMinor = amountMinor;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
