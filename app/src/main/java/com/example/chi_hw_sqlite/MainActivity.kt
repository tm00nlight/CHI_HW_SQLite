package com.example.chi_hw_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chi_hw_sqlite.data.*
import com.example.chi_hw_sqlite.db.DBManager

class MainActivity : AppCompatActivity() {

    private var dbManager: DBManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbManager = DBManager(this)
        insertFaculties() //заполняется список факультетов
        insertSpecializations()
        insertSubjects()
        insertPrograms()
        insertStudents()
        insertFaculties() //повторно добавляется список факультетов
        fetchFaculties() //текущий перечень считывается
        deleteFaculties() //удаляются факультеты с айди больше 5
        updateFaculties() //актуаизируется инфа по 5-му факультету (т.к. уникальных 4)
        fetchFaculties() //считывается перечень после всех изменений

        fetchJoinedData()
    }

    private fun insertFaculties() {
        dbManager?.insertFaculties(
            listOf(
                Faculty(1, "Факультет інформаційних техноогій", "ФІТ"),
                Faculty(2, "Факультет торгівлі та маркетингу", "ФТМ"),
                Faculty(3, "Факультет економіки, менеджменту та права", "ФЕМП"),
                Faculty(4, "Факультет фінансів та обліку", "ФФО")
            )
        )
    }

    private fun insertSpecializations() {
        dbManager?.insertSpecializations(
            listOf(
                Specialization(1, "Інженерія програмного забезпечення", 1),
                Specialization(2, "Маркетинг та реклама", 2),
                Specialization(3, "Міжнародне право", 3)
            )
        )
    }

    private fun insertSubjects() {
        dbManager?.insertSubjects(
            listOf(
                Subject(1, "Основи програмування"),
                Subject(2, "Математичне моделювання"),
                Subject(3, "Психологія"),
                Subject(4, "Маркетинг"),
                Subject(5, "Правознавство"),
                Subject(6, "Інформатика"),
                Subject(7, "Цивільне право")
            )
        )
    }

    private fun insertPrograms() {
        dbManager?.insertPrograms(
            listOf(
                Program(1, 1, 56),
                Program(2, 1, 28),
                Program(2, 3, 24),
                Program(3, 3, 24),
                Program(2, 4, 56)
            )
        )
    }

    private fun insertStudents() {
        dbManager?.insertStudents(
            listOf(
                Student(1, "Вячеслав", "Шевчук", 2, 1, 7),
                Student(2, "Мефодій", "Розум", 2, 1, 3),
                Student(3, "Марина", "Кудрява", 2, 3, 1),
                Student(4, "Кирило", "Ромашка", 1, 1, 2),
                Student(5, "Вікторія", "Чапля", 1, 1, 5),
                Student(6, "Олександр", "Великий", 3, 1, 1),
                Student(7, "Роксолана", "Горова", 3, 1, 4),
                Student(8, "Іван", "Молодець", 1, 2, 2),
            )
        )
    }

    private fun fetchFaculties() {
        val faculties = dbManager?.fetchFaculties()
        faculties?.forEach {
            println(it.toString())
        }
    }

    private fun deleteFaculties() {
        dbManager?.deleteFaculties()
    }

    private fun updateFaculties() {
        dbManager?.updateFaculties()
    }

    private fun fetchJoinedData() {
        println(dbManager?.fetchJoinedData())
    }
}