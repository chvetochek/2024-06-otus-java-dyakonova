package ru.otus.listener.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

public class HistoryListener implements Listener, HistoryReader {
    List<Message> msgList = new ArrayList<>();

    @Override
    public void onUpdated(Message msg) {
        msgList.add(msg);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return msgList.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }
}
