package ferancini.app.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalcCompleto extends AppCompatActivity {

    private EditText edtExpression;
    //guardar se estamos digitando um decimal
    //se estiver digitando um decimal usamos esse estado para impedir
    //o usuário de clicar em . de novo
    private boolean TYPING_DECIMAL_STATE = false;
    private String LAST_OPERATION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_completo);
        edtExpression = findViewById(R.id.edtExpression);
    }

    public void clickDigit(View v){
        String expr = edtExpression.getText().toString();
        String tag = v.getTag().toString();
        expr += tag;
        edtExpression.setText(expr);
    }

    public void clickOperation(View v){
        String expr = edtExpression.getText().toString();
        String tag = v.getTag().toString();

        //estavamos digitando um número decimal
        //ao clicar em uma operação devemos permitir digitar outro numero decimal
        TYPING_DECIMAL_STATE = false;

        expr += tag;
        edtExpression.setText(expr);
    }

    public void clickDecimal(View v){
        String expr = edtExpression.getText().toString();
        String tag = v.getTag().toString();
        //se já estamos digitando um decimal não permitimos clicar de novo no botao .
        if(TYPING_DECIMAL_STATE)
            tag = "";

        //se clicou em . estamos digitando um decimal
        TYPING_DECIMAL_STATE = true;

        expr += tag;
        edtExpression.setText(expr);
    }

    public void clickBackSpace(View v){
        StringBuilder sbExpr = new StringBuilder(this.edtExpression.getText().toString());
        if(sbExpr.length() > 0)
            sbExpr.deleteCharAt(sbExpr.length()-1);
        edtExpression.setText(sbExpr.toString());
    }

    public void clickClear(View v){
        edtExpression.setText("");
    }

    public void clickResult(View v){
        //TODO: aqui ocorre a mágica
        //tratar primeiro multiplicação e divisão
        //subtração e adição podem ser feitas a primeira que achar
        String expr = edtExpression.getText().toString();
        expr.replace('x','*');
        try {

        }
        catch(Exception e){
            Context context = getApplicationContext();
            CharSequence text = "Expressão inválida";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,10);
            toast.show();
        }

    }

}