package models.BankAccount;

public class SavingsBankAccount extends BankAccount {

    private static final String TABLE_NAME = "savings_accounts";
    private double interestRate;

    // Constructors
    public SavingsBankAccount() {
        super();
        this.interestRate = 9.99;
        this.accountType = "Savings Bank Account";
    }

    @Override
    public void updateBalance(double newBalance) {
        this.balance = newBalance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Getters and Setters for interestRate
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Other methods specific to SavingsBankAccount
}
