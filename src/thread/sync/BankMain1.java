package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankMain1 {
    public static void main(String[] args) throws InterruptedException {
        BankAccountV1 account = new BankAccountV1(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        // 악의적인 사용자가 동시에 돈을 출금한다고 가정
        // 동시에 발생해서 검증 로직을 무력화 시킨다
        // 완전히 동일하게 실행된다면 , 200이 나오고
        // 동일하게 실행되지 않아 실행되면, -600이 된다
        t1.start();
        t2.start();
        sleep(500); // 검증 완료까지 잠시 대기
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());
        t1.join();
        t2.join();

        log("최종 잔액: " + account.getBalance());
    }
}
