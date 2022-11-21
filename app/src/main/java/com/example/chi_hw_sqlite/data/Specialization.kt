package com.example.chi_hw_sqlite.data

data class Specialization(
    val id: Int,
    var name: String,
    var idFaculty: Int
) {
    companion object {
        const val TABLE = "Specializations"
        const val ID = "id"
        const val NAME = "name"
        const val ID_FACULTY = "id_faculty"
        const val CODE = "code"
    }
}
