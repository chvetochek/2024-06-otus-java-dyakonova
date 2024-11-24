package ru.otus;

public interface TestLoggingInterface {

    void calculation(int param1);
    void calculation(int param1, int param2);
    void calculation(int param1, int param2, String param3);
    void calculation(int param1, String param2, String param3);
    void alsoCalculation(int param1);
    void alsoCalculation(int param1, int param2);
    void alsoCalculation(int param1, int param2, String param3);
    void alsoCalculation(int param1, String param2, String param3);

}
