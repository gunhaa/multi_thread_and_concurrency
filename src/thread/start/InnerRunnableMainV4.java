package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV4 {

    // 람다 사용
    public static void main(String[] args) {
        log("main() start");

        Thread thread = new Thread(() -> log("run()"));
        
        thread.start();

        log("main() end");
    }
}
