package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        // 구현체에 있는 메서드라 js식 타입 내로잉이 필요함
        // 캐스팅하는 자바식 코드라고도 부르며, 
        // ThreadPoolExecutor tpe = (ThreadPoolExecutor) executorService;
        // 위 코드와 같은 동작을 한다
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            // 스레드 풀에서 관리되는 스레드의 숫자
            int pool = poolExecutor.getPoolSize();
            // 작업을 수행하는 스레드의 숫자
            int active = poolExecutor.getActiveCount();
            // poolExecutor의 작업이 이곳에 담긴다
            // 스레드에 대기중인 작업의 숫자
            int queued = poolExecutor.getQueue().size();
            // 완료된 작업의 숫자
            long completedTaskCount = poolExecutor.getCompletedTaskCount();
            log("[pool= " + pool + ", active= " + active + ", queuedTasks= " + queued + ", completedTask= " + completedTaskCount + "]");
        } else {
            log(executorService);
        }
    }

}
