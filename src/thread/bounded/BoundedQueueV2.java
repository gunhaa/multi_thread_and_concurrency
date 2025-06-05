package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        // 기다리도록 while문으로 변경
        // 언젠가는 가져간다고 가정
        // 무한 대기 문제가 생긴다(락을 가지고 대기해서 생산자,소비자의 행동이 블로킹 당한다)
        while (queue.size() == max) {
            log("[put] 큐가 가득참, 생산자 대기");
            // yield 등 다양하게 사용 가능
            sleep(1000);
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            sleep(1000);
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
