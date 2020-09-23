package ferancini.app.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;

public class CalcSimples extends AppCompatActivity {

    private EditText edtValor1;
    private EditText edtValor2;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_simples);
        this.edtValor1 = findViewById(R.id.edtValor1);
        this.edtValor2 = findViewById(R.id.edtValor2);
        this.txtResultado = (TextView) findViewById(R.id.txtResultado);
    }

    public void clickOperador(View v){
        String resultado = "O Resultado é : ";
        //entrada não pode ser string vazia
        boolean validInput = !edtValor1.getText().toString().equals("") && !edtValor2.getText().toString().equals("");
        //entrada não pode ser só caracteres especiais - e .
        validInput = validInput && !edtValor1.getText().toString().equals("-") && !edtValor2.getText().toString().equals("-");
        validInput = validInput && !edtValor1.getText().toString().equals(".") && !edtValor2.getText().toString().equals(".");
        if(validInput){
            BigDecimal val1 = new BigDecimal(edtValor1.getText().toString());
            BigDecimal val2 = new BigDecimal(edtValor2.getText().toString());

            String tag = v.getTag().toString();

            if (tag.equals("add")){
                resultado += val1.add(val2).toString();
            }
            else if (tag.equals("sub")){
                resultado += val1.subtract(val2).toString();
            }
            else if (tag.equals("mult")){
                resultado += val1.multiply(val2).toString();
            }
            else {
                resultado += val1.divide(val2, 10, BigDecimal.ROUND_CEILING).toString();
            }
        }

        txtResultado.setText(resultado);
    }
}