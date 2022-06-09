package appsisagenda.controle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class actAgendaPessoalMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_agenda_pessoal_menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mninflater = getMenuInflater();
        mninflater.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mniSair :
                System.exit(0);
                break;
            case R.id.mniAgendaPessoal :
                Intent intent = new Intent(this,actAgendaPessoalControle.class);
                startActivity(intent);
                //startActivity(new Intent(this,actAgendaPessoalControle.class));
                break;
            case R.id.mniAPLista:
                Intent aplista = new Intent(this,actAPConsultarLista.class);
                startActivity(aplista);
                //startActivity(new Intent(this,actAPConsultarLista.class));
                break;
        }
        return true;
    }
}