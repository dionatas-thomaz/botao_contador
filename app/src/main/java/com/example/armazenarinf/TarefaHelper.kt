package com.example.armazenarinf
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TarefaHelper(context: Context) :
    SQLiteOpenHelper(context, "tarefas.db", null, 1) {

    companion object {

        const val TABLE_NAME = "tarefas"

        const val COL_ID = "id"
        const val COL_DESCRICAO = "descricao"
        const val COL_RESPONSAVEL = "responsavel"
        const val COL_PORCENTAGEM = "porcentagem"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            
            CREATE TABLE $TABLE_NAME (
            
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_DESCRICAO TEXT,
                $COL_RESPONSAVEL TEXT,
                $COL_PORCENTAGEM INTEGER
            
            )
            
        """

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

        onCreate(db)
    }
    fun inserirTarefa(tarefa: Tarefa): Long {

        val db = this.writableDatabase

        val values = ContentValues()

        values.put(COL_DESCRICAO, tarefa.descricao)
        values.put(COL_RESPONSAVEL, tarefa.responsavel)
        values.put(COL_PORCENTAGEM, tarefa.porcentagem)

        val result = db.insert(TABLE_NAME, null, values)

        db.close()

        return result
    }
    fun listarTarefas(): ArrayList<Tarefa> {

        val lista = ArrayList<Tarefa>()

        val db = this.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COL_ID)
                )

                val descricao = cursor.getString(
                    cursor.getColumnIndexOrThrow(COL_DESCRICAO)
                )

                val responsavel = cursor.getString(
                    cursor.getColumnIndexOrThrow(COL_RESPONSAVEL)
                )

                val porcentagem = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COL_PORCENTAGEM)
                )

                lista.add(
                    Tarefa(
                        id,
                        descricao,
                        responsavel,
                        porcentagem
                    )
                )

            } while (cursor.moveToNext())
        }

        cursor.close()

        return lista
    }
    fun atualizarTarefa(tarefa: Tarefa): Int {

        val db = this.writableDatabase

        val values = ContentValues()

        values.put(COL_DESCRICAO, tarefa.descricao)
        values.put(COL_RESPONSAVEL, tarefa.responsavel)
        values.put(COL_PORCENTAGEM, tarefa.porcentagem)

        val sucesso = db.update(
            TABLE_NAME,
            values,
            "$COL_ID = ?",
            arrayOf(tarefa.id.toString())
        )

        db.close()

        return sucesso
    }
    fun deletarTarefa(id: Int): Int {

        val db = this.writableDatabase

        val sucesso = db.delete(
            TABLE_NAME,
            "$COL_ID = ?",
            arrayOf(id.toString())
        )

        db.close()

        return sucesso
    }
}