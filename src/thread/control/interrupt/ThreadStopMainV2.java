package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        // 이 코드의 문제는 중간에 중지가 안되고, sleep(3000) 이 끝난 이후 다시 runFlag의 상태를 찾을 때 중지시킬 수 있다
        sleep(4000);
        log("작업 중단 지시 thread.interrupt()");
        // 스레드를 인터럽트 상태로 만든다
        // InterruptedException을 던지는 메서드를 호출하거나 호출 중일 때 예외가 발생한다
        thread.interrupt();
        log("work 스레드 인터럽트 상태 1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) { // 인터럽트 체크 안함
                    log("작업 중");
                    // interrupt가 발생할 경우 catch 구문으로 넘어간다
                    // 여기서만 인터럽트 발생
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message= " + e.getMessage());
                log("state = " + Thread.currentThread().getState());
            }

            log("자원 정리");
            log("자원 종료");
        }
    }
}
