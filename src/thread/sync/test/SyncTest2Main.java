package thread.sync.test;

import static util.MyLogger.log;

public class SyncTest2Main {

    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        t1.start();
        t2.start();
    }

    // 스레드의 스택영역에 localValue가 들어간다
    // 지역 변수는 스레드의 개별 저장 공간인 스택영역에 생성된다
    // 지역 변수는 동기화에 대한 걱정을 하지 않아도 된다
    // t1의 localValue와 t2의 localValue는 다른 값이다
    // 이 스택영역은 누구와도 공유하지 않는다
    // 그래서 동시성 문제가 생기지 않는다
    static class MyCounter {
        public void count() {
            int localValue = 0;
            for (int i=0; i < 1000; i++){
                localValue = localValue +1;
            }
            log("result: " + localValue);
        }
    }
}
