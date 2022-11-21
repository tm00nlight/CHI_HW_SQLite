package com.example.chi_hw_sqlite.data

data class Subject(
    val id: Int,
    var name: String
) {
    companion object {
        const val TABLE = "Subjects"
        const val ID = "id"
        const val NAME = "name"
    }
}
