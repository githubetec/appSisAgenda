package appsisagenda.controle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import util.DBAgenda;

public class actAgendaPessoalControle extends AppCompatActivity {

    Button btnSair,btnAlterar,btnCadastrar,btnExcluir,btnListar,btnConsultar;
    EditText edtCodigo,edtNome,edtCelular,edtEmail;

    private int codigo=0;

    DBAgenda dbagenda=null;
    SQLiteDatabase db = null;
    ContentValues valores = null;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_agenda_pessoal_controle);

        btnSair=findViewById(R.id.btnSair);
        btnAlterar = findViewById(R.id.btnAlterar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnListar = findViewById(R.id.btnListar);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtCelular = findViewById(R.id.edtCelular);

        dbagenda = new DBAgenda(getBaseContext());

        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    public void LimparEDT(View view) {
        edtCodigo.setText(null);
        edtNome.setText(null);
        edtCelular.setText("");
        edtEmail.setText("");
    }
/*
    public void StatusBtnEdicao() {
        if(!btnCadastrar.isEnabled()) {
            btnCadastrar.setEnabled(true);
            btnAlterar.setEnabled(false);
            btnExcluir.setEnabled(false);
        }
        else {
            btnCadastrar.setEnabled(false);
            btnAlterar.setEnabled(false);
            btnExcluir.setEnabled(false);
        }
    }

    public void StatusBtnSelecaoLista() {
        if(!btnConsultar.isEnabled()) {
            btnConsultar.setEnabled(true);
            btnListar.setEnabled(true);
        }
        else {
            btnConsultar.setEnabled(false);
            btnListar.setEnabled(false);
        }
    }
*/
    public void Inserir(View view) {
        db=dbagenda.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome",edtNome.getText().toString());
        valores.put("email",edtEmail.getText().toString());
        valores.put("celular",edtCelular.getText().toString());
        long linha = db.insert("agendapessoal",null,valores);
        Toast.makeText(this,"Cadastro Realizado com Sucesso",Toast.LENGTH_LONG).show();
        LimparEDT(view);
        db.close();
    }

    public void ConsultarLista(View view) {
        Intent aplista = new Intent(this,actAPConsultarLista.class);
        startActivity(aplista);
    }

    public void ConsultarCodigo(View view) {
        db=dbagenda.getReadableDatabase();
        cursor=db.rawQuery("select * from agendapessoal where codigo="+ Integer.parseInt(edtCodigo.getText().toString()),null);
        if (cursor.moveToFirst()) {
            edtNome.requestFocus();
            edtNome.setText(cursor.getString(1));
            edtEmail.setText(cursor.getString(2));
            edtCelular.setText(cursor.getString(3));
            btnCadastrar.setEnabled(false);
            btnExcluir.setEnabled(true);
            btnAlterar.setEnabled(true);
            btnConsultar.setEnabled(false);
            btnListar.setEnabled(false);
        }
        else {
            Toast.makeText(this,"Código não Cadastrado",Toast.LENGTH_LONG).show();
            LimparEDT(view);
        }
        db.close();
    }

    public void Alterar(View view) {
        db = dbagenda.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome",edtNome.getText().toString());
        valores.put("email",edtEmail.getText().toString());
        valores.put("celular",edtCelular.getText().toString());
        int linha = db.update("agendapessoal", valores, "codigo=" + Integer.parseInt(edtCodigo.getText().toString().trim()), null);
        if(linha>0) {
            Toast.makeText(this,"Cadastro Atualizado",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"falha na Atualização de Dados",Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void Excluir(View view) {
        db=dbagenda.getWritableDatabase();
        int linha = db.delete("agendapessoal","codigo="+ Integer.parseInt(edtCodigo.getText().toString().trim()),null);
        if(linha>0) {
            Toast.makeText(this,"Cadastro Excluido",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"falha na Exclusão de Dados",Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void Sair(View view) {
        this.finish();
    }

}