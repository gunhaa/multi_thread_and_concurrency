package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");

        thread1.start();

        // 잠시 대기하여 thread-1이 park 상태에 빠질 시간을 준다
        // WAITING 상태변화를 위해 필요한 시간
        sleep(100);
        log("Thread-1 state: " + thread1.getState());
    
        log("main -> unpark(Thread-1)");
        // LockSupport.unpark(thread1); // 1. unpark사용

        // unpark가 아닌 interrupt를 통해서도 깨어날 수 있다
//        thread1.interrupt(); // 2. interrupt 사용
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            // RUNNABLE 상태에서 WAITING 상태로 바꾼다
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
