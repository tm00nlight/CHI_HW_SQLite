package com.example.chi_hw_sqlite.data

data class Student(
    val id: Int,
    val firstName: String,
    var surname: String,
    val idSpecialization: Int,
    var course: Int,
    var group: Int
) {
    companion object {
        const val TABLE = "Students"
        const val ID = "id"
        const val FIRST_NAME = "first_name"
        const val SURNAME = "surname"
        const val ID_SPECIALIZATION = "id_specialization"
        const val COURSE = "course_num"
        const val GROUP = "group_num"
    }
}
