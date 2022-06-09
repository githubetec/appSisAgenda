package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAgenda extends SQLiteOpenHelper {

    /*
    SQLiteDatabase: Classe que contém os métodos de manipulação dos dados no banco;
    SQLiteOpenHelper: Classe responsável pela criação do banco e também responsável pelo versionamento do mesmo.
    */


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="dbgestaoescolar.db";

    private String tblagendapessoal="create table if not exists agendapessoal " +
            "(codigo integer primary key autoincrement," +
            " nome varchar(40)," +
            " email varchar(50)," +
            " celular varchar(16));";

    public DBAgenda(Context contexto)
    {
        super(contexto,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(tblagendapessoal);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {

    }

}
