package com.example.chi_hw_sqlite.data

data class Program(
    val idSpecialization: Int,
    val idSubject: Int,
    var hours: Int
) {
    companion object {
        const val TABLE = "Programs"
        const val ID_SPECIALIZATION = "id_spec"
        const val ID_SUBJECT = "id_subj"
        const val HOURS = "hours"
    }
}
