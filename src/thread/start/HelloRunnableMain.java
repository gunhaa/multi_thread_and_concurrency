package thread.start;

public class HelloRunnableMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+ ": main() start");

        // 스레드를 상속받는 방식보다 runnable 상속 받는 방법이 더 낫다
        // 실제로도 Runnable interface 구현을 더 많이 사용한다
        
        // why? 자바는 다중 상속이 불가능하기때문에 구현으로 하는 것이 더 낫다
        // 인터페이스 사용하는 방법보다 유연성이 떨어진다
        // Runnable 사용시 Thread와 분리되어 명확해진다

        // 단점은 코드가 약간 더 복잡해진다(Thread에 Runnable 인터페이스 전달)
        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread = new Thread(helloRunnable);
        thread.start();

        System.out.println(Thread.currentThread().getName()+ ": main() end");
    }
}
