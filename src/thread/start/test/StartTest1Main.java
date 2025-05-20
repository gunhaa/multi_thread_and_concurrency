package thread.start.test;

import static util.MyLogger.log;

public class StartTest1Main {
    public static void main(String[] args) {
        /*
        다음 요구사항에 맞게 멀티스레드 프로그램을 작성해라.
        1. Thread 클래스를 상속 받은 CounterThread라는 스레드 클래스를 만들자
        2. 이 스레드는 1부터 5까지의 숫자를 1초 간격으로 출력해야한다, 앞서 만든 log를 이용해서 출력
        3. main() 메서드에서 CounterThread 스레드 클래스를 만들고 실행
        */
        class CounterThread extends Thread{
            @Override
            public void run(){
                for (int i = 1; i <= 5; i++) {
                    log("value: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        CounterThread counterThread = new CounterThread();
        counterThread.start();
    }
}
