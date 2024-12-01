package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        // группирует выходящий список по name, при этом суммирует поля value
        var groupByNameMap = data.stream().collect(Collectors.groupingBy(Measurement::name, Collectors.summingDouble(Measurement::value)));
        return groupByNameMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
