package com.example.myapplication;

import java.util.Objects;
import java.util.UUID;

/**
 * Данные о произвольном ребёнке
 */
public class Kid {
    private String uuid;
    private String surname;
    private String name;
    private String patronymic;
    private String numberLC;

    public Kid(String uuid, String surname, String name, String patronymic, String numberLC) {
        this.uuid = uuid;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.numberLC = numberLC;
    }

    public Kid(String surname, String name, String patronymic, String numberLC) {
        uuid = UUID.randomUUID().toString();
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.numberLC = numberLC;
    }

    public String getUuid() {
        return uuid;
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
                Objects.equals(surname, kid.surname) &&
                Objects.equals(name, kid.name) &&
                Objects.equals(patronymic, kid.patronymic) &&
                Objects.equals(numberLC, kid.numberLC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, surname, name, patronymic, numberLC);
    }

    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }
}