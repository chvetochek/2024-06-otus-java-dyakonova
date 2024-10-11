package ru.otus;

public class Section {
    public Denomination getNom() {
        return nom;
    }

    private final Denomination nom;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

    public Section(Denomination nom) {
        this.nom = nom;
        count = 0;
    }

    public void add(int count) {
        this.count += count;
    }
}
