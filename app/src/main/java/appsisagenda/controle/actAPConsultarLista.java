package appsisagenda.controle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import util.DBAgenda;

public class actAPConsultarLista extends AppCompatActivity {

    Button btnAnterior,btnProximo;
    EditText edtCodigo,edtNome,edtCelular,edtEmail;
    TextView txtQtdCad;

    DBAgenda dbagenda=null;
    SQLiteDatabase db = null;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_apconsultar_lista);
        dbagenda = new DBAgenda(getBaseContext());
        edtCodigo = findViewById(R.id.edtCodigo);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtCelular = findViewById(R.id.edtCelular);
        txtQtdCad=findViewById(R.id.txtQtdCad);

        db=dbagenda.getReadableDatabase();
        cursor=db.rawQuery("select * from agendapessoal ",null);

        ConsultarLista();
    }

    public void ExibeCadastro() {
        edtNome.requestFocus();
        edtCodigo.setText(cursor.getString(0));
        edtNome.setText(cursor.getString(1));
        edtEmail.setText(cursor.getString(2));
        edtCelular.setText(cursor.getString(3));
    }

    public void ConsultarLista() {
        if (cursor.moveToFirst()) {
            txtQtdCad.setText(" - Cadastros: " + cursor.getCount());
            ExibeCadastro();
        }
        else {
            Toast.makeText(this,"Código não Cadastrado",Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void Proximo(View view) {
        if(!cursor.isLast()) {
            cursor.moveToNext();
            ExibeCadastro();
        }
    }

    public void Anterior(View view) {
        if(!cursor.isFirst()) {
            cursor.moveToPrevious();
            ExibeCadastro();
        }
    }

    public void Sair(View view) {
        this.finish();
    }

}