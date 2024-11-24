package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class LogAspect {

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface)
                Proxy.newProxyInstance(LogAspect.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private final Map<String, List<Class<?>[]>> methodsWithLogAnnotation = new HashMap<>();

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            findMethodsWithLogAnnotation();
        }

        private void findMethodsWithLogAnnotation() {
            for (var method : myClass.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Log.class)) {
                    methodsWithLogAnnotation.computeIfAbsent(method.getName(), param -> new ArrayList<>()).add(method.getParameterTypes());
                }
            }
        }

        private boolean isMethodWithLogAnnotation(Method method) {
            var parametersList = methodsWithLogAnnotation.get(method.getName());
            if (parametersList != null) {
                for (var parametersArray : parametersList) {
                    if (Arrays.equals(parametersArray, method.getParameterTypes())) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isMethodWithLogAnnotation(method)) {
                System.out.println("executed method:" + method.getName() + ", params: " + Arrays.asList(args));
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
