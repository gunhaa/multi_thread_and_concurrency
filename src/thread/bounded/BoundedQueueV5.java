package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;
    private final Lock lock = new ReentrantLock();

    // condition은 스레드가 대기하는 대기집합
    private final Condition producerCond = lock.newCondition();
    private final Condition consumerCond = lock.newCondition();

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {

        lock.lock();
        try {
            while (queue.size() == max) {
                log("[put] 큐가 가득참, 생산자 대기");
                try {
                    // Object.wait() 대신 사용
                    // 생산자용 대기 공간에 넣기
                    producerCond.await();
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            // 대기자들에게 알린다
            log("[put] 생산자 데이터 저장, signal() 호출");
            // Object.notify() 대신 사용
            // 소비자 집단에다가 신호 넣기
            consumerCond.signal();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String take() {

        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    consumerCond.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take] 소비자 데이터 획득, signal() 호출");
            producerCond.signal();
            return data;

        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
