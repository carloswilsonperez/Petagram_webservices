package com.example.macbookair.petagram;

import android.content.Intent;
import android.os.StrictMode;
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

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    //Declaracion de variables del formulario
    private EditText inNombre;
    private EditText inEmail;
    private EditText inDescripcion;
    private Button btnSiguiente;
    String recipiente=null;
    String asunto=null;
    String mensaje=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        miActionBar.setLogo(R.drawable.dog_footprint_24);
        setSupportActionBar(miActionBar);
        TextView tituloAppBar = (TextView) findViewById(R.id.tvTituloApp); //Para cambiar el titulo del AppBar al nombre del activity
        tituloAppBar.setText(R.string.ab_titulo_Main);

        //Inicia codigo boton de subir, ya declaramos la clase en el manifest como hija
        // Obtenemos support ActionBar correspondiente a esta toolbar
        ActionBar miUpButton = getSupportActionBar();
        // Activamos el Boton de subir
        miUpButton.setDisplayHomeAsUpEnabled(true);

        inNombre = (EditText) findViewById(R.id.inNombre);
        inEmail = (EditText) findViewById(R.id.inEmail);
        inDescripcion = (EditText) findViewById(R.id.inDescripcion);

        //Codigo para guardar datos del formulario
        guardarDatos();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.miActionBar);
        tb.inflateMenu(R.menu.mainmenu);
        menu.getItem(2).setVisible(false);
        tb.setOnMenuItemClickListener(
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

            case R.id.mAcercaDe:
                // En caso que el usuario haga click en el menu, ir al Activity de Acerca de...
                Intent intentADA = new Intent(ContactoActivity.this, AcercaDeActivity.class);
                startActivity(intentADA);
                finish();
                return true;


            case R.id.action_favorite:
                // En caso que el usuario haga click en Favoritos, lo llevamos a la ventana de Favoritos
                Intent intent = new Intent(ContactoActivity.this, MascotasFavoritas.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.mConfigurarCuenta:
                // En caso que el usuario haga click en el menu, ir al Activity de Acerca de...
                Intent intentCCA = new Intent(ContactoActivity.this, ConfigurarCuentaActivity.class);
                startActivity(intentCCA);
                finish();
                return true;

            default:
                // Si llegamos aqui, la entrada del usuario no fue reconocida.
                // Invocamos a la superclase para que la maneje.
                return super.onOptionsItemSelected(item);

        }
    }

    public void guardarDatos(){

        btnSiguiente = (Button) findViewById(R.id.button2);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                enviarCorreo();
                                            }
                                        }
        );
    }


    public void enviarCorreo() {

        Toast.makeText(getApplicationContext(), "Enviando eMail", Toast.LENGTH_SHORT).show();

        recipiente = inEmail.getText().toString();
        asunto = inNombre.getText().toString();
        mensaje = inDescripcion.getText().toString();

        Properties props = new Properties();
        props.put("mail.smtp.user", "petagram1111@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom("petagram1111@gmail.com");
            msg.setRecipients(Message.RecipientType.TO,
                    recipiente);
            msg.setSubject(asunto + ": Petagram hizo Contacto Exitoso");
            msg.setSentDate(new Date());
            msg.setText(mensaje);
            Transport.send(msg, "petagram1111@gmail.com", "Petagram11");

            Transport transport = session.getTransport("smtps");
            transport.connect("smtp.gmail.com", 465, "petagram1111@gmail.com", "Petagram11");
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
        Toast.makeText(getApplicationContext(), "eMail enviado", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ContactoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    //Codigo para en caso que el usuario presione el boton back, reconstruir el activity main
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(ContactoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
