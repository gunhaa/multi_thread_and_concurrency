package thread.control;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();
        
        // toString()이 호출된다
        // 스레드id, 스레드 이름, 우선순위, 스레드 그룹을 포함하는 문자열을 반환
        log("mainThread = " + mainThread);
        // 자동으로 만들어주고 절대로 중복되지 않는다
        log("mainThread.threadId() = " + mainThread.threadId());
        log("mainThread.getName() = " + mainThread.getName());
        // 운영체제 스케쥴러에게 주는 힌트, 우선순위가 높다고해서 무조건 실행되진 않는다(운영체제 스케쥴러에 따라 다름)
        // 백엔드 어플리케이션 개발에서 거의 조정하지 않는다
        log("mainThread.getPriority() = " + mainThread.getPriority());
        // 기본적으로 모든 스레드는 부모스레드와 동일한 스레드 그룹에 속하게 된다
        // 하나의 그룹으로 묶어서 일괄 종료, 우선순위 설정 등을 할 수 있다
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
        // Thread.State 열거형에 정의 된 상수 중 하나이다(현재 상태 반환)
        // 매우 중요하다        // - NEW: 스레드가 아직 시작되지 않은 상태(생성만함)
        // - RUNNABLE: 스레드가 실행 중이거나 실행될 준비가 된 상태
        // - BLOCKED: 스레드가 동기화 락을 기다리는 상태이다(LOCK)
        // - WAITING: 스레드가 다른 스레드의 특정 작업이 완료되기를 기다리는 상태이다
        // - TIME_WAITING: 일정시간동안 기다리는 상태이다.(sleep)
        // - TERMINATED: 스레드가 실행을 마친 상태이다
        log("mainThread.getState() = " + mainThread.getState());

        System.out.println("--------------------------------------------------");
        // myThread
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("myThread = " + myThread);
        log("myThread.threadId() = " + myThread.threadId());
        log("myThread.getName() = " + myThread.getName());
        log("myThread.getPriority() = " + myThread.getPriority());
        log("myThread.getThreadGroup() = " + myThread.getThreadGroup());
        log("myThread.getState() = " + myThread.getState());
    }
}
