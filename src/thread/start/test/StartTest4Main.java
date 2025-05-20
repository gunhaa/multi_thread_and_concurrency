package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {
    /*
       Thread-A, Thread-B 두 스레드 생성
       Thread-A는 1초에 한번씩 "A" 출력
       Thread-B는 0.5초에 한번씩 "B" 출력
       이 프로그램은 강제 종료시까지 계속 실행된다
    */


    // Runnable을 구현하는 객체를 만들고, 필드를 content sleepMs를 생성자로 받아서 하면
    // 더 효율적인 코드로 작성할 수 있다..
    // 해당 방식 개선 가능
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while(true){
                log("A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread-A");

        Thread threadB = new Thread(() -> {
            while(true){
                log("B");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread-B");
        threadA.start();
        threadB.start();
    }

}
