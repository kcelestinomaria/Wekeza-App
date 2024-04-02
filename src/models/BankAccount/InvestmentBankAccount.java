package models.BankAccount;

public class InvestmentBankAccount extends BankAccount {
    
    private String investmentType;

    // Constructor
    public InvestmentBankAccount(int accountId, String accountNumber, double balance, String investmentType){
        super(accountId, accountNumber, balance);
        this.investmentType = investmentType;
    }

    // getters & setters

    // Additional methods specific to InvestmentAccount
}
