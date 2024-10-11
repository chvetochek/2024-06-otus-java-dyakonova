package ru.otus;

public enum Denomination {
    RUB100(100),
    RUB200(200),
    RUB500(500),
    RUB1000(1000),
    RUB2000(2000),
    RUB5000(5000);

    private final int denomination;

    Denomination(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

    @Override
    public String toString() {
        return "Номинал: " + denomination + " руб";
    }
}
