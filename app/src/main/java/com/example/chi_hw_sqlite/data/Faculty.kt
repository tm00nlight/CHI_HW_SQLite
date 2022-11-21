package com.example.chi_hw_sqlite.data

data class Faculty(
    val id: Int,
    var name: String,
    var shortName: String
) {
    companion object {
        const val TABLE = "Faculties"
        const val ID = "id"
        const val NAME = "name"
        const val SHORT_NAME = "short_name"
    }
}
