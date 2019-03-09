package appmoviles.com.semana7eco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SInginActivity extends AppCompatActivity {

    private EditText et_correo;
    private EditText et_pass;
    private Button btn_singin;
    private Comunicacion com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        com = Comunicacion.getRef();

        et_correo = findViewById(R.id.et_correo);
        et_pass = findViewById(R.id.et_pass);
        btn_singin = findViewById(R.id.btn_singin);

        btn_singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.enviar(et_correo.getText().toString());
            }
        });
    }
}
