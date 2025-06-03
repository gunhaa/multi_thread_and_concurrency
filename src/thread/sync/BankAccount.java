package thread.sync;

public interface BankAccount {

    // 출금
    // 출금할 금액을 매개 변수로, (잔액을 확인후) 성공할경우 true 실패할경우 false
    boolean withDraw(int amount);
    
    // 잔고 확인
    int getBalance();
}
