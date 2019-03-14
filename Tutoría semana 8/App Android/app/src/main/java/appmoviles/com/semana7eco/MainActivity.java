package appmoviles.com.semana7eco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    Button btn_enviar;
    Comunicacion referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referencia = Comunicacion.getRef();
        referencia.addObserver(this);


        btn_enviar = findViewById(R.id.btn_enviar);
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referencia.enviar("Hola desde Android");
            }
        });


    }

    @Override
    public void update(Observable o, Object arg) {
        final String recibido = (String) arg;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               //Ejecuta en el GUI
               btn_enviar.setText(recibido);
            }
        });

    }
}
