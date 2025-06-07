package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CasMainV3 {

    private static int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() + "resultvalue : " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get(); // t0, t1 모두 0을 읽는다
//            sleep(100); //스레드 동시 실행을 위한 대기
            log("getValue: " + getValue);
            // value++가 아닌 값을 바꾸는 것이다 0과 1
            // 다른 스레드가 값을 바꿔버리면 실패하는게 CAS 연산이다
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        } while (!result);
        
        // 다른 스레드가 값을 변경할 수 있어 해당 방법은 위험하다
//        return atomicInteger.get();
        return getValue + 1;
    }
}
