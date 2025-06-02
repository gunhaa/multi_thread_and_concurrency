package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        log("runFlag = " + myTask.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        myTask.runFlag = false;
        log("runFlag = " + myTask.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {

//        boolean runFlag = true;
        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");
            
            while(runFlag) {
                // runFlag가 false로 변하면 탈출
                // sout을 하면, 컨텍스트 스위칭이 일어나서 메모리 값을 읽어서 해결 가능하다(volatile을 사용하지 않을 경우)
//                System.out.println("hello");
            }
            
            log("task 종료");
        }
    }
}
