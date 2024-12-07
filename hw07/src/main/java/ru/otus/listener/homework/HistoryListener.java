package ru.otus.listener.homework;

import java.util.*;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

public class HistoryListener implements Listener, HistoryReader {
    List<Message> msgList = new ArrayList<>();

    @Override
    public void onUpdated(Message msg) {
        var objectForMessage = new ObjectForMessage();
        objectForMessage.setData(new ArrayList<>(msg.getField13().getData()));
        var storedMsg = msg.toBuilder();
        storedMsg.field13(objectForMessage);
        msgList.add(storedMsg.build());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return msgList.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }
}
