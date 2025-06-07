package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger);

//        int result = atomicInteger.incrementAndGet();
//        System.out.println("result = " + result);
//
//        int result2 = atomicInteger.incrementAndGet();
//        System.out.println("result2 = " + result2);

        int resultValue1 = incrementAndGet(atomicInteger);
        System.out.println("resultValue1 = " + resultValue1);
        int resultValue2 = incrementAndGet(atomicInteger);
        System.out.println("resultValue2 = " + resultValue2);

    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get(); // thread1: 0
            log("getValue: " + getValue);
            // value++가 아닌 값을 바꾸는 것이다 0과 1
            // 다른 스레드가 값을 바꿔버리면 실패하는게 CAS 연산이다
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        } while (!result);
        return getValue + 1;
    }
}
