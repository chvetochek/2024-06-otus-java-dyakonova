package ru.otus.dataprocessor;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ru.otus.model.Measurement;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load(){
        // читает файл, парсит и возвращает результат
        var fileIs = getClass().getClassLoader().getResourceAsStream(fileName);
        var objectMapper = JsonMapper.builder().build();
        Measurement[] measurement;
        try {
            measurement = objectMapper.readValue(fileIs, Measurement[].class);
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
        return List.of(measurement);
    }
}
