package fr.enssat.firstapp.counter;

public class Counter {

    static final int INIT_VALUE = 0;

    int value = INIT_VALUE;

    public int get() {
        return value;
    }

    public void inc() {
        value++;
    }

    public boolean isInit() {
        return value == INIT_VALUE;
    }
}
