package ru.otus.appcontainer;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

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
                Object component = null;
                try {
                    var paramTypes = method.getParameterTypes();
                    var args = new ArrayList<>();
                    for (var parameterType : paramTypes) {
                        var appComponent = getAppComponent(parameterType);
                        args.add(appComponent);
                    }

                    component = method.invoke(configClass.getDeclaredConstructor().newInstance(), args.toArray());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                appComponents.add(component);
                appComponentsByName.put(compName, method);
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
        for (var appComponent : appComponents) {
            if (componentClass.isAssignableFrom(appComponent.getClass())) {
                return (C) appComponent;
            }
        }
        throw new RuntimeException("Не найдено нужного компонента");
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
