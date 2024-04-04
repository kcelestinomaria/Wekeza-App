package models.BankAccount;

public class InvestmentBankAccount extends BankAccount {

    private String investmentType;

    // Constructor
    public InvestmentBankAccount(String investmentType) {
        super();
        this.investmentType = investmentType;
        this.accountType = "Investment Bank Account";
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

    // Getters and Setters for investmentType
    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

  
    // Other methods specific to InvestmentBankAccount
}
