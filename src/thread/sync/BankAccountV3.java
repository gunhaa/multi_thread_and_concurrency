package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {

    // volatile을 넣어도 해결되지 않는다
//    private volatile int balance;
    private int balance;

    public BankAccountV3(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withDraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        // synchronized 블록을 사용해 최적화
        // 진짜 임계영역, 내 인스턴스 지정
        synchronized (this) {
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }

            // 잔고가 출금액 보다 많으면, 진행
            log("[검증 성공] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance = balance - amount;
            log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
        }
        // 진짜 임계영역 종료
        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return this.balance;
    }
}
