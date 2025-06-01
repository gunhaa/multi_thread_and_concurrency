package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV2 {

    public static void main(String[] args) {
        log("Start");
        thread.control.join.JoinMainV1.SumTask task1 = new thread.control.join.JoinMainV1.SumTask(1, 50);
        thread.control.join.JoinMainV1.SumTask task2 = new thread.control.join.JoinMainV1.SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread1");
        Thread thread2 = new Thread(task2, "thread2");
        thread1.start();
        thread2.start();

        // 해결 방법1
        // 정확한 타이밍을 맞추어 기다리기 어려움
        log("main 스레드 sleep()");
        sleep(3000);
        log("main스레드 깨어남");

        // 해결 방법2
        // while문으로 thread의 상태추적(terminated)
        // while(thread1.getState() != Thread.State.TERMINATED);

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);
        int result = task1.result + task2.result;

        log("result = " + result);
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
