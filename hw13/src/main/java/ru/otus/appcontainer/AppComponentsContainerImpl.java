package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@SuppressWarnings("squid:S1068")
public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...
        var allMethods = Arrays.stream(configClass.getDeclaredMethods()).sorted(Comparator.comparing(method -> method.getAnnotation(AppComponent.class).order())).toList();
        for (var method : allMethods) {
            if (method.isAnnotationPresent(AppComponent.class)) {
                var compName = method.getAnnotation(AppComponent.class).name();
                if (appComponentsByName.containsKey(compName)) {
                    throw new RuntimeException("Компонентт с таким именем уже существует: " + compName);
                }
                Object component;
                var paramTypes = method.getParameterTypes();
                var args = Arrays.stream(paramTypes)
                        .map(this::getAppComponent)
                        .toArray();
                try {
                    component = method.invoke(configClass.getDeclaredConstructor().newInstance(), args);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                appComponents.add(component);
                appComponentsByName.put(compName, component);
            }
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        C component = null;
        for (var appComponent : appComponents) {
            if (componentClass.isAssignableFrom(appComponent.getClass())) {
                if (component != null) {
                    throw new RuntimeException("Дубликат");
                }
                component = (C) appComponent;
            }
        }
        if (component == null) {
            throw new RuntimeException("Не найдено нужного компонента");
        }
        return component;
    }


    @Override
    public <C> C getAppComponent(String componentName) {
        var comp = appComponentsByName.get(componentName);
        if (comp == null) {
            throw new RuntimeException("Компонентт с таким именем не найден: " + componentName);
        }
        return (C) comp;
    }
}
