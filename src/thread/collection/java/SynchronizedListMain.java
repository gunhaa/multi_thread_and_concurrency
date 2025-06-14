package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {
    public static void main(String[] args) {
        // 동기화 프록시 리스트 생성방법
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("data1");
        list.add("data2");
        list.add("data3");
        // class java.util.Collections$SynchronizedRandomAccessList
        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}
