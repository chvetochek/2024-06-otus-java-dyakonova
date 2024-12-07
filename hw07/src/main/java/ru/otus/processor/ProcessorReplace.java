package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorReplace implements Processor{
    @Override
    public Message process(Message message) {
        var oldField11Value = message.getField11();
        return message.toBuilder().field11(message.getField12()).field12(oldField11Value).build();}
}
