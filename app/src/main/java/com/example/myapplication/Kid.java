package com.example.myapplication;

import java.util.Objects;
import java.util.UUID;

/**
 * Данные о произвольном ребёнке
 */
public class Kid {
    private final String uuid;
    private int number;
    private String surname;
    private String name;
    private String patronymic;
    private String numberLC;

    public Kid(String uuid, int number, String surname, String name, String patronymic, String numberLC) {
        this.uuid = uuid;
        this.number = number;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.numberLC = numberLC;
    }

    public Kid(String surname, String name, String patronymic, String numberLC) {
        uuid = UUID.randomUUID().toString();
        number = 0;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.numberLC = numberLC;
    }

    public String getUuid() {
        return uuid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNumberLC() {
        return numberLC;
    }

    public void setNumberLC(String numberLC) {
        this.numberLC = numberLC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kid kid = (Kid) o;
        return Objects.equals(uuid, kid.uuid) &&
                Objects.equals(number, kid.number) &&
                Objects.equals(surname, kid.surname) &&
                Objects.equals(name, kid.name) &&
                Objects.equals(patronymic, kid.patronymic) &&
                Objects.equals(numberLC, kid.numberLC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, number, surname, name, patronymic, numberLC);
    }

    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }
}
