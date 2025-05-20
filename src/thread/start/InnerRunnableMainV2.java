package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV2 {

    // 익명 클래스 사용방법
    public static void main(String[] args) {
        log("main() start");

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                log("run()");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end");
    }
}
