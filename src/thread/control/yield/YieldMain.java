package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty
                // sleep(1); // 2. sleep // 특정 스레드 휴식
                // CPU에서 실행해야 하지만 CPU 스케쥴링 큐로 이동시킨다(양보한다)
                // java의 스레드가 RUNNABLE 상태일떄 운영체제의 스케쥴링은 2가지 상태를 가질 수 있다
                // 실행상태(Running) 스레드가 CPU에서 실제로 실행 중이다
                // 실행 대기 상태(Ready): 스레드가 실행될 준비가 되었지만, CPU가 바빠서 스케줄링 큐에서 대기 중이다
                // 자바에서는 이 두 상태를 구분할 수 없다
                //Thread.yield(); // 3. yield // 다른 스레드에 실행을 양보
                // 운영체제 스케쥴러에게 힌트를 주는것이 yield() 이다

            }
        }
    }
}
