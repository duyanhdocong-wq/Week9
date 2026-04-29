package bank_system;

import java.util.ArrayList; // Nhập cụ thể, không dùng wildcard
import java.util.List;
import java.util.Objects;

import exceptions.BankException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidFundingAmountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Account {

    // Tích hợp Logger để theo dõi trạng thái hệ thống
    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    // Đặt tên hằng số theo chuẩn UPPER_SNAKE_CASE
    public static final String CHECKING_TYPE = "CHECKING";
    public static final String SAVINGS_TYPE = "SAVINGS";

    // Đặt lại tên biến rõ nghĩa, xóa bỏ gạch dưới và viết hoa sai chuẩn
    private long accountNumber;
    private double balance;
    protected List<Transaction> transactionList;

    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionList = new ArrayList<>();
    }

    // Các phương thức getter và setter được định dạng lại chuẩn xác
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        if (transactionList == null) {
            this.transactionList = new ArrayList<>();
        } else {
            this.transactionList = transactionList;
        }
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    protected void doDepositing(double amount) throws InvalidFundingAmountException {
        if (amount <= 0) { // Thêm khoảng trắng quanh toán tử
            throw new InvalidFundingAmountException(amount);
        }
        balance += amount;
        logger.info("Nạp tiền thành công vào TK {}: +{}", accountNumber, amount);
    }

    protected void doWithdrawing(double amount) throws BankException {
        // Tung ra ngoại lệ cụ thể thay vì Exception chung chung
        if (amount <= 0) {
            throw new InvalidFundingAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
        logger.info("Rút tiền thành công từ TK {}: -{}", accountNumber, amount);
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionList.add(transaction);
        }
    }

    /**
     * Lấy lịch sử giao dịch bằng StringBuilder để tối ưu hiệu năng.
     */
    public String getTransactionHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lịch sử giao dịch của tài khoản ").append(accountNumber).append(":\n");

        for (int i = 0; i < transactionList.size(); i++) {
            sb.append(transactionList.get(i).getTransactionSummary());
            if (i < transactionList.size() - 1) {
                sb.append("\n");
            }
        }

        // Thay thế System.out bằng logger.debug để phục vụ truy vết
        logger.debug("Đã truy xuất lịch sử cho tài khoản: {}", accountNumber);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        return accountNumber == other.accountNumber;
    }

    @Override
    public int hashCode() {
        // Sử dụng Objects.hash để format code gọn gàng và chuẩn xác
        return Objects.hash(accountNumber);
    }
}