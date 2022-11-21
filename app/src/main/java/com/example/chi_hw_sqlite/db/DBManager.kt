package com.example.chi_hw_sqlite.db

import android.content.ContentValues
import android.content.Context
import com.example.chi_hw_sqlite.data.*

class DBManager(context: Context) {
    private val dataBaseHelper = DataBaseHelper(context)

    fun insertFaculties(list: List<Faculty>) {
        val db = dataBaseHelper.writableDatabase

        val cv = ContentValues()
        list.forEach { faculty ->
            cv.apply {
//                put(Faculty.ID, faculty.id)
                put(Faculty.NAME, faculty.name)
                put(Faculty.SHORT_NAME, faculty.shortName)
            }
            db.insert(Faculty.TABLE, null, cv)

            cv.clear()
        }

        db.close()
    }

    fun fetchFaculties(): List<Faculty> {
        val db = dataBaseHelper.readableDatabase
        val cursor = db.query(
            Faculty.TABLE, null, null, null,
            null, null, null
        )

        if (cursor != null && cursor.count > 0) {
            val faculties = ArrayList<Faculty>(cursor.count)
            cursor.moveToFirst()
            do {
                var index = cursor.getColumnIndex(Faculty.ID)
                val id = cursor.getInt(index)

                index = cursor.getColumnIndex(Faculty.NAME)
                val name = cursor.getString(index)

                index = cursor.getColumnIndex(Faculty.SHORT_NAME)
                val shortName = cursor.getString(index)

                faculties.add(Faculty(id, name, shortName))
            } while (cursor.moveToNext())

            cursor.close()
            db.close()
            return faculties
        }

        db.close()
        return emptyList()
    }

    fun deleteFaculties() {
        val db = dataBaseHelper.writableDatabase
        db.delete(Faculty.TABLE, "${Faculty.ID} > 5", null)

        db.close()
    }

    fun updateFaculties() {
        val db = dataBaseHelper.writableDatabase

        val cv = ContentValues().apply {
            put(Faculty.NAME, "Факультет банківської справи")
            put(Faculty.SHORT_NAME, "ФБС")
        }
        db.update(Faculty.TABLE, cv, "${Faculty.ID} = 5", null)

        db.close()
    }

    fun insertSpecializations(list: List<Specialization>) {
        val db = dataBaseHelper.writableDatabase

        val cv = ContentValues()
        list.forEach { specialization ->
            cv.apply {
                put(Specialization.NAME, specialization.name)
                put(Specialization.ID_FACULTY, specialization.idFaculty)
            }
            db.insert(Specialization.TABLE, null, cv)

            cv.clear()
        }

        db.close()
    }

    fun insertSubjects(list: List<Subject>) {
        val db = dataBaseHelper.writableDatabase

        val cv = ContentValues()
        list.forEach { subject ->
            cv.apply {
                put(Subject.NAME, subject.name)
            }
            db.insert(Subject.TABLE, null, cv)

            cv.clear()
        }

        db.close()
    }

    fun insertPrograms(list: List<Program>) {
        val db = dataBaseHelper.writableDatabase

        val cv = ContentValues()
        list.forEach { program ->
            cv.apply {
                put(Program.ID_SPECIALIZATION, program.idSpecialization)
                put(Program.ID_SUBJECT, program.idSubject)
                put(Program.HOURS, program.hours)
            }
            db.insert(Program.TABLE, null, cv)

            cv.clear()
        }

        db.close()
    }

    fun insertStudents(list: List<Student>) {
        val db = dataBaseHelper.writableDatabase

        val cv = ContentValues()
        list.forEach { student ->
            cv.apply {
                put(Student.FIRST_NAME, student.firstName)
                put(Student.SURNAME, student.surname)
                put(Student.ID_SPECIALIZATION, student.idSpecialization)
                put(Student.COURSE, student.course)
                put(Student.GROUP, student.group)
            }
            db.insert(Student.TABLE, null, cv)

            cv.clear()
        }

        db.close()
    }

    fun fetchJoinedData() {
        val db = dataBaseHelper.readableDatabase

        //select st.first_name, st.surname, spec.name, st.course_num, st.group_num from students st
        // inner join specializations spec on st.id_specialization = spec.id order by st.course_num asc;
        val joinQuery =
            "select st.${Student.FIRST_NAME}, st.${Student.SURNAME}, spec.${Specialization.NAME}, " +
                    "st.${Student.COURSE}, st.${Student.GROUP} from ${Student.TABLE} st inner join ${Specialization.TABLE} spec " +
                    "on st.${Student.ID_SPECIALIZATION} = spec.${Specialization.ID} order by st.${Student.COURSE} asc;"
        val cursor = db.rawQuery(joinQuery, null)

        cursor.close()
        db.close()
    }
}