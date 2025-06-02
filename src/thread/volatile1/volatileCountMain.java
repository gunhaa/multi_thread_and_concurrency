package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class volatileCountMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(1000);

        myTask.flag = false;
        log("flag = " + myTask.flag + ", count = " + myTask.count + " in main thread");
    }

    static class MyTask implements Runnable {

//        boolean flag = true;
//        long count;

        volatile boolean flag = true;
        volatile long count;

        @Override
        public void run() {
            while(flag) {
                count++;
                // 1억번에 한번씩 출력
                if(count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count);
                }
            }
            log("flag = " + flag + ", count = " + count + " end in work thread");
        }
    }
}
