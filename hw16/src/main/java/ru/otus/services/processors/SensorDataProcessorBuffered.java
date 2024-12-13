package ru.otus.services.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.SensorDataProcessor;
import ru.otus.api.model.SensorData;
import ru.otus.lib.SensorDataBufferedWriter;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

// Этот класс нужно реализовать
@SuppressWarnings({"java:S1068", "java:S125"})
public class SensorDataProcessorBuffered implements SensorDataProcessor {
    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBuffered.class);

    private final int bufferSize;
    private final SensorDataBufferedWriter writer;
    Queue<SensorData> dataBuffer = new ConcurrentLinkedQueue<>();
    List<SensorData> bufferedData = new CopyOnWriteArrayList<>();

    public SensorDataProcessorBuffered(int bufferSize, SensorDataBufferedWriter writer) {
        this.bufferSize = bufferSize;
        this.writer = writer;
    }

    @Override
    public void process(SensorData data) {
        dataBuffer.add(data);
        bufferedData.add(data);
        log.info("Обработка данных: {}", data);
        if (dataBuffer.size() >= bufferSize) {
            log.info("Переполнение");
            flush();
        }
    }

    public void flush() {
        try {
            writer.writeBufferedData(bufferedData);
            bufferedData.clear();
            dataBuffer.clear();
            log.info("Очистил данные");
        } catch (Exception e) {
            log.error("Ошибка в процессе записи буфера", e);
        }
    }

    @Override
    public void onProcessingEnd() {
        flush();
    }
}
