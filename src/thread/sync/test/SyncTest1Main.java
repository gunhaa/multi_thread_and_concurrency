package thread.sync.test;

public class SyncTest1Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i< 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("result: " + counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count = count + 1;
        }
        // count = count + 1
        // 이 연산은 3가지 연산이 발생한다
        // 1. count를 읽는다
        // 2. 읽은 count의 값에 1을 더한다
        // 3. 더한 결과를 count에 다시 저장한다
        // 여러 스레드가 접근하면, READ, WRITE, SAVE 연산이 동시에 실행되어 섞일 수 있다

        public int getCount() {
            return count;
        }
    }
}
