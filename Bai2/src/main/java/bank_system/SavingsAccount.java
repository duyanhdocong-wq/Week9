package bank_system;

import exceptions.InsufficientFundsException;
import exceptions.InvalidFundingAmountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SavingsAccount extends Account {
    private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);

    // Khai báo hằng số để tránh Magic Numbers
    private static final double MAX_WITHDRAW_LIMIT = 1000.0;
    private static final double MIN_BALANCE_REQUIRED = 5000.0;

    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            addTransaction(new Transaction(Transaction.TYPE_DEPOSIT_SAVINGS,
                    amount, initialBalance, finalBalance));
            logger.info("Nạp tiền vào TK tiết kiệm {} thành công", getAccountNumber());
        } catch (Exception e) {
            logger.error("Lỗi nạp tiền: {}", e.getMessage());
        }
    }

    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            if (amount > MAX_WITHDRAW_LIMIT) { // Dùng hằng số [cite: 105]
                throw new InvalidFundingAmountException(amount);
            }
            if (initialBalance - amount < MIN_BALANCE_REQUIRED) { // Dùng hằng số [cite: 106]
                throw new InsufficientFundsException(amount);
            }
            doWithdrawing(amount);
            double finalBalance = getBalance();
            addTransaction(new Transaction(Transaction.TYPE_WITHDRAW_SAVINGS,
                    amount, initialBalance, finalBalance));
            logger.info("[SAVINGS] Rút tiền thành công. Số dư còn: {}", finalBalance);
        } catch (Exception e) {
            logger.error("Rút tiền tiết kiệm thất bại: {}", e.getMessage());
        }
    }
}