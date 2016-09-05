package com.example.macbookair.petagram;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    private EditText inNombreUsuario;
    private Button btnGuardar;
    String NombreCTA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        inNombreUsuario = (EditText) findViewById(R.id.inNombreUsuario);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        miActionBar.setLogo(R.drawable.dog_footprint_24);
        setSupportActionBar(miActionBar);
        TextView tituloAppBar = (TextView) findViewById(R.id.tvTituloApp); //Para cambiar el titulo del AppBar al nombre del activity
        tituloAppBar.setText(R.string.ab_titulo_ConfigurarCuenta);

        //Inicia codigo boton de subir, ya declaramos la clase en el manifest como hija
        // Obtenemos support ActionBar correspondiente a esta toolbar
        ActionBar miUpButton = getSupportActionBar();
        // Activamos el Boton de subir
        miUpButton.setDisplayHomeAsUpEnabled(true);

        guardarDatos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        miActionBar.setLogo(R.drawable.dog_footprint_24);
        TextView tituloAppBar = (TextView) findViewById(R.id.tvTituloApp); //Para cambiar el titulo del AppBar al nombre del activity
        tituloAppBar.setText(R.string.ab_titulo_Main);
        miActionBar.inflateMenu(R.menu.mainmenu);
        miActionBar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intentContacto = new Intent(ConfigurarCuentaActivity.this, ContactoActivity.class);
                startActivity(intentContacto);
                finish();
                return true;

            case R.id.mAcercaDe:
                // En caso que el usuario haga click en el menu, ir al Activity de Acerca de...
                Intent intentADA = new Intent(ConfigurarCuentaActivity.this, AcercaDeActivity.class);
                startActivity(intentADA);
                finish();
                return true;

            case R.id.action_favorite:
                // En caso que el usuario haga click en Favoritos, lo llevamos a la ventana de Favoritos
                Intent intent = new Intent(ConfigurarCuentaActivity.this, MascotasFavoritas.class);
                startActivity(intent);
                finish();
                return true;

            default:
                // Si llegamos aqui, la entrada del usuario no fue reconocida.
                // Invocamos a la superclase para que la maneje.
                return super.onOptionsItemSelected(item);

        }
    }

    public void guardarDatos(){

        btnGuardar = (Button) findViewById(R.id.buttonGuardar);
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                NombreCTA = inNombreUsuario.getText().toString();
                    Toast.makeText(ConfigurarCuentaActivity.this, NombreCTA+" Se a guardado", Toast.LENGTH_SHORT).show();

                }
            }
        );
    }

    public String getNombreCuenta(){

        if (NombreCTA == null){
            NombreCTA="khalyluna";
        }
        return NombreCTA;

    }
    //Codigo para en caso que el usuario presione el boton back, reconstruir el activity main
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(ConfigurarCuentaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
