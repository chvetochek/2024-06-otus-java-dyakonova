package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinterConsole;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.*;

import java.time.LocalDateTime;
import java.util.List;

public class HomeWork {

    /*
    Реализовать to do:
      1. done Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
      2. Сделать процессор, который поменяет местами значения field11 и field12
      3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
            Секунда должна определяьться во время выполнения.
            Тест - важная часть задания
            Обязательно посмотрите пример к паттерну Мементо!
      4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
         Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
         Для него уже есть тест, убедитесь, что тест проходит
    */
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);
    public static void main(String[] args) {
        /*
          по аналогии с Demo.class
          из элеменов "to do" создать new ComplexProcessor и обработать сообщение
        */
        var processors = List.of(new ProcessorExceptional(LocalDateTime::now), new LoggerProcessor(new ProcessorReplace()));

        var complexProcessor = new ComplexProcessor(processors, ex -> {});
        var listenerPrinter = new ListenerPrinterConsole();
        complexProcessor.addListener(listenerPrinter);

        var objectData = new ObjectForMessage();
        var message = new Message.Builder(1L)
                .field11("field11")
                .field12("field12")
                .field13(objectData)
                .build();

        var result = complexProcessor.handle(message);
        logger.info("result:{}", result);

        complexProcessor.removeListener(listenerPrinter);
    }
}