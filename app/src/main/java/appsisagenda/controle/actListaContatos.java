package appsisagenda.controle;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import util.DBAgenda;

public class actListaContatos extends AppCompatActivity {

    //ListView lstContatos ;
    TextView txtLista;

    DBAgenda dbagenda=null;
    SQLiteDatabase db = null;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_lista_contatos);

        dbagenda = new DBAgenda(getBaseContext());
        db=dbagenda.getReadableDatabase();
        cursor=db.rawQuery("select * from agendapessoal ",null);

        //lstContatos = (ListView) findViewById(R.id.lstContatos);
        txtLista=findViewById(R.id.txtLista);
        ArrayList<String> lstCadastros = new ArrayList<String>();

        if(cursor.moveToFirst()) {
            for (int idx = 0; idx <= cursor.getCount() - 1; idx++) {
                lstCadastros.add(cursor.getString(0) + " - " +
                                 cursor.getString(1) + " - " +
                                 cursor.getString(2) + " - " +
                                 cursor.getString(3));
                 //lstCadastros.add(new String()[{"a","b"}]);
                cursor.moveToNext();
                txtLista.setText(txtLista.getText().toString()+lstCadastros.get(idx)+"\n");
            }
        }

        //ArrayAdapter<String> lstAdapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,lstCadastros);
        //lstContatos.setAdapter(lstAdapter);
    }
    public void Fechar(View view) {
        finish();
    }
}