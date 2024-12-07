package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorExceptional implements Processor{
    @Override
    public Message process(Message message) {
        var messageState = save(message);
        if (System.currentTimeMillis()/1000 % 2 == 0) {
            throw new RuntimeException("Выполнение произошло в четную секунду");
        }
        return messageState.getMessage();
    }

    public MessageState save(Message message) {
        return new MessageState(message);
    }

    public class MessageState {

        private final Message message;

        public MessageState(Message message) {
            this.message = message;
        }

        public Message getMessage() {
            return message;
        }
    }
}


