package bank.service;

public class SendMessageToAccHolder {
    private Long AccountNumber;
    private String operation;
    private double amount;
    private String message;

    public SendMessageToAccHolder(Long accountNumber, String operation, double amount, String message) {
        AccountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
        this.message = message;
    }

    public Long getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
