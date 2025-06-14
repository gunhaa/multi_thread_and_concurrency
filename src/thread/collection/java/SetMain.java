package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {
        Set<Integer> copySet = new CopyOnWriteArraySet<>();
        copySet.add(1);
        copySet.add(3);
        copySet.add(2);
        // 순서를 보장하지 않는다
        System.out.println("copySet = " + copySet);

        // 순서를 보장한다(comparator을 인자로 넣음)
        // TreeSet 대안
        Set<Integer> skipSet = new ConcurrentSkipListSet<>();

        skipSet.add(1);
        skipSet.add(3);
        skipSet.add(2);

        System.out.println("skipSet = " + skipSet);
    }
}
