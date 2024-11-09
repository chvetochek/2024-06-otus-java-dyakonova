package ru.otus;

public class TestLogging implements TestLoggingInterface{
    @Log
    public void calculation(int param1){}

    @Log
    public void calculation(int param1, int param2){}

    @Log
    public void calculation(int param1, int param2, String param3){}
}
