package thread.bounded;

// 버퍼의 역할을 하는 큐의 인터페이스
public interface BoundedQueue {
    // 데이터 보관(생산자 스레드가 호출, 데이터 생산)
    void put(String data);
    // 데이터 보관 값을 가져간다(소비자 스레드가 호출, 데이터 소비)
    String take();
}
