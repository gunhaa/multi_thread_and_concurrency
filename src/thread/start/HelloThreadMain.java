package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName()+ ": start() 호출 전");

        // start() 메서드는 스레드를 실행하는 아주 특별한 메서드 이다
        // start() 를 호출하면 HelloThread가 run()메서드를 실행한다
        
        // 스레드는 실행 시간과 순서를 보장하지 않는다, 이 말이 멀티 스레드의 핵심이다
        helloThread.start();
        System.out.println(Thread.currentThread().getName()+ ": start() 호출 후");

        System.out.println(thread.getName() + ": main() end");
    }

}
