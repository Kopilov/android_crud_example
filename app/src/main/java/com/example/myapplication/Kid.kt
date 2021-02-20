package com.example.myapplication

import java.util.*

data class Kid (val  uuid: String, val number: Int, val surname: String, val name: String, val patronymic: String, val numberLC: String) {

    constructor(surname: String, name: String, patronymic: String, numberLC: String): this(UUID.randomUUID().toString(), 0, surname, name, patronymic, numberLC)

    public fun getFullName(): String {
        return "$surname $name $patronymic";
    }
}
