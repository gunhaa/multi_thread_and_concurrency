package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {
        log("Start");
        thread.control.join.JoinMainV1.SumTask task1 = new thread.control.join.JoinMainV1.SumTask(1, 50);
        thread.control.join.JoinMainV1.SumTask task2 = new thread.control.join.JoinMainV1.SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread1");
        Thread thread2 = new Thread(task2, "thread2");
        thread1.start();
        thread2.start();

        // 스레드가 종료될때까지 대기
        log("join - main 스레드가 thread1, thread2 종료까지 대기 - WAITING");
        // join을 호출하는 스레드는 WAITING이 되며, 대상 스레드가 TERMINATED 상태가 될 때 까지 대기한다
        // 대상 스레드가 TERMINATED가 되면 호출 스레드는 다시 RUNNABLE 상태가 되면서 다음 코드를 수행한다
        // 다른 스레드가 완료 될 때까지 무기한 기다리는 단점이 있다
        // 중간에 포기하지 못하고 자리가 날 때까지 무기한 대기한다
        // 메서드 오버로딩 join(ms)를 사용하면 해결 가능하다
        thread1.join();
        thread2.join();
        log("main 스레드 대기 완료");

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
