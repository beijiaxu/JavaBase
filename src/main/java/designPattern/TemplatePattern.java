package designPattern;

/**
 * 模版方法模式
 * Created by Xu on 2017/4/4.
 */
public class TemplatePattern {
    public static void main(String[] args) {
        Account account = new MoneyMarketAccount();
        System.out.println("货币市场账号的利息数额为：" + account.calculateInterest());
        account = new CDAccount();
        System.out.println("定期账号的利息数额为：" + account.calculateInterest());
    }
}

abstract class Account {
    public final double calculateInterest() {
        double interestRate = doCalculateInterestRate();
        String accountType = doCaculateAccountType();
        double amount = calculateAmount(accountType);
        return amount * interestRate;
    }

    protected abstract String doCaculateAccountType();

    protected abstract double doCalculateInterestRate();

    private double calculateAmount(String accountType) {
        return 1111.00;
    }
}

class MoneyMarketAccount extends Account {

    @Override
    protected String doCaculateAccountType() {
        return "Money Market";
    }

    @Override
    protected double doCalculateInterestRate() {
        return 0.045;
    }
}

class CDAccount extends Account {

    @Override
    protected String doCaculateAccountType() {
        return "Certificate of Deposite";
    }

    @Override
    protected double doCalculateInterestRate() {
        return 0.06;
    }
}


