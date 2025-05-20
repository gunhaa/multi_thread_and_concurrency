package thread.start;

public class BadThreadMain {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName()+ ": start() 호출 전");
        // start()를 사용하지 않는다면 main에서 호출되버린다
        // 스레드 생성이아닌 그냥 메서드로써 실행이 된다
        // start()를 통해 시스템콜이 이루어져 KLT가 만들어지기 떄문에 반드시 호출해야한다
        helloThread.run();
        System.out.println(Thread.currentThread().getName()+ ": start() 호출 후");

        System.out.println(thread.getName() + ": main() end");
    }
}
