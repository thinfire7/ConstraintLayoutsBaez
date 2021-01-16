package co.edu.unipiloto.constraintlayouts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void enviarMensaje(View view) {

        EditText mensaje[];
        CheckBox sintomas[];
        String datos[] = new String[5];
        String datosGuardados = "";

        mensaje = new EditText[]{(EditText)findViewById(R.id.txtFieldEmail), (EditText)findViewById(R.id.txtFieldAsunto)};

        sintomas = new CheckBox[]{(CheckBox)findViewById(R.id.checkBoxTosSeca),(CheckBox)findViewById(R.id.checkBoxFiebre),(CheckBox)findViewById(R.id.checkBoxPerdidaOlfato),(CheckBox)findViewById(R.id.checkBoxDolorDeCabeza)
                ,(CheckBox)findViewById(R.id.checkBoxConjuntivitis)};

        for (int i = 0; i < 5; i++){
            if(sintomas[i].isChecked()==true){
                datos[i] = sintomas[i].getText().toString();
            }else{
                datos[i] = "-";
            }
        }

        for (int i = 0; i < 5; i++){
            if(datos[i].equals("-")==false){
                datosGuardados += datos[i] + "\n";
            }
        }
        datosGuardados = datosGuardados.trim();

        String mensajeSaludo="";


        LocalDateTime locaDate = LocalDateTime.now();
        int hours  = locaDate.getHour();
        //int minutes = locaDate.getMinute();
        //int seconds = locaDate.getSecond();

        if(hours<12)
            mensajeSaludo="Buenos dias";
        if(hours>12 && hours<18)
            mensajeSaludo="Buenas tardes";
        if(hours>18)
            mensajeSaludo="Buenas noches";

        datosGuardados = mensajeSaludo + " doctor, he sufrido los siguientes sintomas:\n" + datosGuardados;


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + mensaje[0].getText().toString()));

        intent.putExtra(Intent.EXTRA_SUBJECT,mensaje[1].getText().toString());

        intent.putExtra(Intent.EXTRA_TEXT,datosGuardados);

        startActivity(intent);

    }
}