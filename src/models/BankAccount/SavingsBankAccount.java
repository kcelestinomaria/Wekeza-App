package models.BankAccount;

public class SavingsBankAccount extends BankAccount {
    
    private static final String TABLE_NAME = "savings_bank_account";
    private double interestRate;

    // Constructors
    public SavingsBankAccount(int accountId, String accountNumber, double balance, double interestRate){
        super(accountId, accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void updateBalance(double newBalance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBalance'");
    }

    @Override
    public void deposit(double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    @Override
    public boolean withdraw(double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }

    @Override
    public void setAccountId(int accountId2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAccountId'");
    }

    @Override
    public void setAccountNumber(long accountNumber2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAccountNumber'");
    }

    @Override
    protected Object getBalance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }

    @Override
    protected Object getAccountId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountId'");
    }

    @Override
    protected Object getAccountNumber() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountNumber'");
    }

    // Getters & Setters
    

    // Additional methods specific to SavingsAccount

    
}
