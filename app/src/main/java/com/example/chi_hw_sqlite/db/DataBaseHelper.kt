package com.example.chi_hw_sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.chi_hw_sqlite.data.*

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createFacultySql = "create table ${Faculty.TABLE} (" +
                Faculty.ID + " integer primary key autoincrement, " +
                Faculty.NAME + " text not null, " +
                Faculty.SHORT_NAME + " text not null);"
        db?.execSQL(createFacultySql)

        val createSubjectSql = "create table ${Subject.TABLE} (" +
                Subject.ID + " integer primary key autoincrement, " +
                Subject.NAME + " text not null);"
        db?.execSQL(createSubjectSql)

        val createSpecializationSql = "create table ${Specialization.TABLE} (" +
                Specialization.ID + " integer primary key autoincrement, " +
                Specialization.NAME + " text not null, " +
                Specialization.ID_FACULTY + " integer, " +
                "foreign key(${Specialization.ID_FACULTY}) references ${Faculty.TABLE}(${Faculty.ID}));"
        db?.execSQL(createSpecializationSql)

        val createProgramSql = "create table ${Program.TABLE} (" +
                Program.ID_SPECIALIZATION + " integer, " +
                Program.ID_SUBJECT + " integer, " +
                Program.HOURS + " integer, " +
                "foreign key(${Program.ID_SPECIALIZATION}) references ${Specialization.TABLE}(${Specialization.ID}), " +
                "foreign key(${Program.ID_SUBJECT}) references ${Subject.TABLE}(${Subject.ID}));"
        db?.execSQL(createProgramSql)

        val createStudentSql = "create table ${Student.TABLE} (" +
                Student.ID + " integer primary key autoincrement, " +
                Student.FIRST_NAME + " text not null, " +
                Student.SURNAME + " text not null, " +
                Student.ID_SPECIALIZATION + " integer, " +
                Student.COURSE + " integer, " +
                Student.GROUP + " integer, " +
                "foreign key(${Student.ID_SPECIALIZATION}) references ${Specialization.TABLE}(${Specialization.ID}));"
        db?.execSQL(createStudentSql)

        val createViewFirstCourse =
            "create view FirstCourse as select ${Student.FIRST_NAME}, ${Student.SURNAME} " +
                    "from ${Student.TABLE} where ${Student.COURSE} = 1;"
        db?.execSQL(createViewFirstCourse)

        val createTriggerAfterDeleteSubject =
            "create trigger del_subj after delete on ${Subject.TABLE} " +
                    "begin delete from ${Program.TABLE} where ${Program.TABLE}.${Program.ID_SUBJECT} = OLD.${Subject.ID}; " +
                    "end;"
        db?.execSQL(createTriggerAfterDeleteSubject)
    }

    override fun onUpgrade(db: SQLiteDatabase?, v0: Int, v1: Int) {
        when (v1) {
            DB_VERSION_RELEASE -> {
                db?.execSQL("drop table if exists " + Student.TABLE)
                db?.execSQL("drop table if exists " + Specialization.TABLE)
                db?.execSQL("drop table if exists " + Faculty.TABLE)
                db?.execSQL("drop table if exists " + Subject.TABLE)
                db?.execSQL("drop table if exists " + Program.TABLE)
                onCreate(db)
            }
            DB_VERSION_BETA -> migrate(db)
        }

    }

    private fun migrate(db: SQLiteDatabase?) {
        val alterSpecializationSql =
            "alter table ${Specialization.TABLE} add ${Specialization.CODE} text"
        db?.execSQL(alterSpecializationSql)
    }

    companion object {
        private const val DB_NAME = "University"
        private const val DB_VERSION = 1
        private const val DB_VERSION_BETA = 2
        private const val DB_VERSION_RELEASE = 3
    }
}