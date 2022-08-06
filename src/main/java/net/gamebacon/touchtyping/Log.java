package net.gamebacon.touchtyping;


import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UtilityClass
public class Log {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void debug(String msg) {

        if(TouchTyping.isDebugEnabled()) {
            final String message = String.format("[%s] [TouchTyping Debug]: %s", LocalDateTime.now().format(dtf), msg);
            System.out.println(message);
        }

    }

}
