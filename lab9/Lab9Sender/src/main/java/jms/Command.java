package jms;

public class Command {
    private String operator;
    private int value;

    public Command() {}
    public Command(String operator, int value) {
        this.operator = operator;
        this.value = value;
    }

    public String getOperator() { return operator; }
    public int getValue() { return value; }
}