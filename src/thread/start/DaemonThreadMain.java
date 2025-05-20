package thread.start;

public class DaemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        DaemonThread daemonThread = new DaemonThread();
        
        // userThread가 기본 설정이다
        // 모든 user 스레드가 종료되면 데몬 스레드는 자동으로 종료된다.
        daemonThread.setDaemon(true); // 데몬 스레드 여부
        daemonThread.start();
        
        
        
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DaemonThread extends Thread{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+ ": run() start");
            try {
                
                // 쓰레드 관련 예외는 밖으로 던질 수 없다
                Thread.sleep(10000); // 10초 대기
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+ ": run() end");
        }
    }
}
