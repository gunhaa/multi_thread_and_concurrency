package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // 비교하고, 같으면 연산
        boolean result1 = atomicInteger.compareAndSet(0, 1); // 1
        // 1. if 기대하는 값이 0 이면, 2. 값을 1로 변경해 -> 원자적이 아닌데?
        // 위 연산은 CPU가 원자적인 연산으로 만들어준다 (Assembly를 하나로 묶는다, CMPXCHG [memory], register)
        // 즉 하드웨어가 연산을 지원한다
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1); // false, 값을 바꾸지 않는다
        System.out.println("result2 = " + result2 + ", value = " + atomicInteger.get());
    }
}
