package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLock {

    // CAS연산을 지원한다
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 획득 시도");

        // CAS연산은 두 연산을 하나의 원자적인 연산으로 만들어준다
        // 1. 락 사용 여부 확인(lock의 값이 false라면) 2. 락의 값 변경(lock의 값을 true로 변경)
        while(!lock.compareAndSet(false, true)) {
            // 락을 획득할 떄 까지 스핀대기(busy waiting) 한다
            log("락 획득 실패- 스핀 대기");
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock.set(false);
        log("락 반납 완료");
    }
}
