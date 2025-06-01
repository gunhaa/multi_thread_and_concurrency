package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {

    public static void main(String[] args) throws InterruptedException {
        log("Start");
        JoinMainV1.SumTask task1 = new JoinMainV1.SumTask(1, 50);
        Thread thread1 = new Thread(task1, "thread1");
        thread1.start();

        // 스레드가 종료될때까지 대기
        // 메서드 오버로딩 join(ms)를 사용하면 해결 가능하다(TIMED-WAITING 상태가 된다)
        thread1.join(1000);
        log("main 스레드 대기 완료");

        log("task1.result = " + task1.result);
        log("End");
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }


        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            this.result = sum;
            log("작업 완료 result = " + this.result);
        }
    }

}
