package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        // 이 코드의 문제는 중간에 중지가 안되고, sleep(3000) 이 끝난 이후 다시 runFlag의 상태를 찾을 때 중지시킬 수 있다
        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        // 스레드를 인터럽트 상태로 만든다
        // InterruptedException을 던지는 메서드를 호출하거나 호출 중일 때 예외가 발생한다
        thread.interrupt();
        log("work 스레드 인터럽트 상태 1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) { // 인터럽트 상태 변경 X
                log("작업 중");
                // interrupt가 발생할 경우 catch 구문으로 넘어간다
                // 여기서도 인터럽트 발생
            }
            // interrupt는 isInterrupted를 바꾸진 않는다
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리");
                // interrupt가 발생하였으나, 상태를 변경하지 않아서 Thread.interrupt = true 상태가 되어, 익셉션이 터진다
                Thread.sleep(1000);
                log("자원 정리 종료");
            } catch (InterruptedException e){
                // exception은 isInterrupted를 바꾼다
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태 3 = " + Thread.currentThread().isInterrupted());
            }
        }
    }
}
