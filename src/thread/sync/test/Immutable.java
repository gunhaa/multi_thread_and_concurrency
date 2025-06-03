package thread.sync.test;

public class Immutable {
    
    // 여러 스레드가 공유 자원에 접근하는 것 자체는 문제가 되지 않는다
    // 공유 자원을 사용하는 중간에 값을 변경하기 때문에 문제가 발생하는 것이다
    // 모든 스레드가 항상 같은 값을 읽기 때문에 안전하다
    // final은 안전한 공유 자원이 된다
    private final int value;

    public Immutable(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
