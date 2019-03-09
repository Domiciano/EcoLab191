package appmoviles.com.semana7eco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private Button singin, singup;
    private Comunicacion com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com = Comunicacion.getRef();
        com.addObserver(this);

        System.out.println("COM: " + com);
        System.out.println("HILO INICIADO");
        singin = findViewById(R.id.singin);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SInginActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void update(Observable observable, Object o) {
        final String mensaje = (String)o;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
