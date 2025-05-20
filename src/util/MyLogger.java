package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class MyLogger {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object obj){
        String time = LocalTime.now().format(formatter);
                                                                                    // object의 toString()이 호출되서 더 유연하게 사용할 수 있다
        System.out.printf("%s [%9s] %s \n", time, Thread.currentThread().getName(), obj);
    }
}
