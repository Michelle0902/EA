
package client;

public class BankOperationMessage {
    private String operationType;
    private long accountNumber;
    private String customerName;
    private double amount;

    public BankOperationMessage() {}

    public BankOperationMessage(String operationType, long accountNumber, String customerName, double amount) {
        this.operationType = operationType;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }

    public long getAccountNumber() { return accountNumber; }
    public void setAccountNumber(long accountNumber) { this.accountNumber = accountNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
