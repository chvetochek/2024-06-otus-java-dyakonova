package ru.otus;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class Atm {
    private final TreeSet<Section> allSection;

    public Atm() {
        allSection = new TreeSet<>(Comparator.comparingInt(section -> section.getNom().getDenomination()));
        allSection.add(new Section(Denomination.RUB500));
        allSection.add(new Section(Denomination.RUB100));
    }

    public void add(HashMap<Denomination, Integer> money) {
        for (Section section : allSection) {
            var sectionNom = section.getNom();
            if (money.containsKey(sectionNom)) {
                section.add(money.get(sectionNom));
                money.remove(sectionNom);
            }
        }
        if (!money.isEmpty()) {
            System.out.println("Не получилось распределить все деньги");
        }
    }

    public void dispense(int money) {
        if (money == 0) {
            System.out.println("Введите сумму больше 0");
            return;
        }

        if (money > getBalance()) {
            System.out.println("Недостаточно средств");
            return;
        }

        var result = exchange(money);
        if (result.isEmpty()) {
            System.out.println("Сумма не может быть выдана, нет размена");
            return;
        }
        System.out.println("Выдано:");
        for (var entry : result.entrySet()) {
            System.out.println(entry.getKey().toString() + " - " + entry.getValue() + "  шт.");
        }
    }

    private int getBalance() {
        int sum = 0;
        for (Section section : allSection) {
            sum += section.getCount() * section.getNom().getDenomination();
        }
        return sum;
    }

    public void printBalance() {
        System.out.println("Сумма на счету: " + getBalance());
    }

    private HashMap<Denomination, Integer> exchange(int money) {
        var result = new HashMap<Denomination, Integer>();
        for (Section section : allSection.reversed()) {
            if (money <= 0) {
                break;
            }

            int denominationValue = section.getNom().getDenomination();
            if (money >= denominationValue) {
                int count = money / denominationValue;

                if (count > 0 && count <= section.getCount()) {
                    section.setCount(section.getCount() - count);
                    result.put(section.getNom(), count);
                    money -= count * denominationValue;
                }
            }
        }
        return result;
    }
}
