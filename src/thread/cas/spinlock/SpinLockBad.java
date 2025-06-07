package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {
    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");
        while (true) {
            
            // volatile로 같은 것을 읽었기 때문에 동시에 실행이 되어 동시성 이슈가 발생한다
            // critical section을 보호하지 못한다
            // 1. 락의 사용여부 확인 2. 락의 값 변경
            // 두 가지가 원자적이지 않다는 문제가 있다

            // 여기서 해결 방안이 있다, 바로 이 두가지 코드를 하나로 묶어 원자적으로 처리하는 것이다
            
            if(!this.lock) { // 1. 락 사용 여부 확인
                sleep(100); // 문제 상황 확인용, 스레드 대기
                this.lock = true; // 2. 락의 값 변경
                break;
            } else {
                // 락을 획득할 때 까지 스핀 대기(바쁜 대기) 한다
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        this.lock = false; // 원자적인 연산
        log("락 반납 완료");
    }
}
