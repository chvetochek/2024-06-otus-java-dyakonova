package ru.otus.listener.homework;

import java.util.*;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

public class HistoryListener implements Listener, HistoryReader {
    final Map<Long, Message> msgList = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        var storedMsg = copyMsg(msg);
        msgList.put(msg.getId(), storedMsg);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(msgList.get(id));
    }

    private Message copyMsg(Message msg) {
        var objectForMessage = new ObjectForMessage();
        objectForMessage.setData(new ArrayList<>(msg.getField13().getData()));
        var storedMsg = msg.toBuilder();
        storedMsg.field13(objectForMessage);
        return storedMsg.build();
    }
}
