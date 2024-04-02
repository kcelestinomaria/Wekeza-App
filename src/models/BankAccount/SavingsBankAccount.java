package models.BankAccount;

public class SavingsBankAccount extends BankAccount {
    
    private static final String TABLE_NAME = "savings_bank_account";
    private double interestRate;

    // Constructors
    public SavingsBankAccount(int accountId, String accountNumber, double balance, double interestRate){
        super(accountId, accountNumber, balance);
        this.interestRate = interestRate;
    }

    // Getters & Setters
    

    // Additional methods specific to SavingsAccount

    
}
