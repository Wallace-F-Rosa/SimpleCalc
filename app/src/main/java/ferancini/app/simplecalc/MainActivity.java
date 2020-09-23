package ferancini.app.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickCalcSimples(View v){
        Intent intent = new Intent(this, CalcSimples.class);
        startActivity(intent);
    }

    public void clickCalcCompleta(View v){
        Intent intent = new Intent(this, CalcCompleto.class);
        startActivity(intent);
    }
}