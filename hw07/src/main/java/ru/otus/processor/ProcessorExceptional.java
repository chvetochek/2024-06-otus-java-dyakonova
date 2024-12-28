package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorExceptional implements Processor{

    private final DateTimeProvider dateTimeProvider;

    public ProcessorExceptional(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeProvider.getDate().getSecond() % 2 == 0) {
            throw new RuntimeException("Выполнение произошло в четную секунду");
        }
        return message;
    }
}


