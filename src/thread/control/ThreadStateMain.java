package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyRunnable(), "myThread");
        log("myThread.start1 = " + myThread.getState());

        log("myThread.start()");
        myThread.start();
        // 병렬 실행이기 때문에 RUNNABLE로 찍힐 가능성이 존재한다(sleep이 없을 경우)
        Thread.sleep(1000);
        log("myThread.state3 = " + myThread.getState());
        Thread.sleep(4000);
        log("myThread.state5 = " + myThread.getState());
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            log("start");
            Thread myThread = Thread.currentThread();
            log("myThread.state2 = " + myThread.getState());
            log("sleep() start");
            try {
                myThread.sleep(3000);
                // 내가 자고있는 상태여서, sleep중인 스레드는 자신을 호출할 수 없다
                // 다른 스레드를 이용해 상태를 확인해야한다
//                log("mystate()" + myThread.getState());
                log("sleep() end");
                log("myThread.state4 = " + myThread.getState());

                log("end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
