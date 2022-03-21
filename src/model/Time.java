package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    // Get current time
    public static String getTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
