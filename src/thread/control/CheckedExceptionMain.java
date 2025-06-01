package thread.control;

public class CheckedExceptionMain {

    // 체크 예외는 밖으로 던질 수 있다
    // 컴파일 단계에서 반드시 처리해야할, 예측 가능한 치명적 오류
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }
    
    static class CheckedRunnable implements Runnable {
        @Override
        public void run() {

        }

        // 해당 코드는 자바 문법 규칙에 맞지않는다(밖으로 던질 수 없음, InterruptedException)
        // 예외의 규칙
        // Runnable 인터페이스에서 체크 예외를 던지지 않기 때문에, 해당 클래스를 오버라이딩하는 경우 예외를 밖으로 던질 수 없는 것이다(컴파일 오류 발생)
        // 자식 메서드는 부모 메서드가 던질 수 있는 체크 예외의 하위 타입만 던질 수 있다

        // 왜 자바는 이런 규칙이 있는가?
        
        // 부모 메서드의 메서드를 호출하는 클라이언트 코드는 부모 메서드가 던지는 특정 예외만을 처리하도록 작성된다
        // 자식 클래스가 더 넓은 범위의 예외를 던지면 해당 코드는 모든 예외를 제대로 처리하지 못할 수 있다
        // 이는 예외 처리의 일관성을 해치고, 예상하지 못한 런타임 오류를 초래할 수 있다
        
//        @Override
//        public void run() throws Exception{
//            throw new Exception();
//        }
    }
}
