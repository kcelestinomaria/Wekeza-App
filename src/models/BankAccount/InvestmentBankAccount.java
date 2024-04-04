package models.BankAccount;

public class InvestmentBankAccount extends BankAccount {
    
    private String investmentType;

    // Constructor
    public InvestmentBankAccount(int accountId, String accountNumber, double balance, String investmentType){
        super(accountId, accountNumber, balance);
        this.investmentType = investmentType;
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

    // getters & setters

    // Additional methods specific to InvestmentAccount
}
