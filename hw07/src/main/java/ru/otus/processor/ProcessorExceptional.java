package ru.otus.processor;

import ru.otus.model.Message;

import java.time.LocalDate;
import java.util.Locale;

public class ProcessorExceptional implements Processor{
    @Override
    public Message process(Message message) {
        if (System.currentTimeMillis()/1000 % 2 == 0) {
            throw new RuntimeException();
        }
        return message;
    }
}
