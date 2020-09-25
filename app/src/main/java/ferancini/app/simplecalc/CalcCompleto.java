package ferancini.app.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcCompleto extends AppCompatActivity {

    private EditText edtExpression;
    //guardar se estamos digitando um decimal
    //se estiver digitando um decimal usamos esse estado para impedir
    //o usuário de clicar em . de novo
    private boolean TYPING_DECIMAL_STATE = false;

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


    public int getOperationIndex(String expr){
        char[] operators = {'+', '-', 'x', '/'};

        for(Character op : operators){
            int i = expr.indexOf(op);
            if(i != -1)
                return i;
        }

        return -1; //nenhum operador encontrado
    }

    public BigDecimal getResult(String expr) throws Exception {
        BigDecimal result = new BigDecimal("0");

        int opIndex = getOperationIndex(expr);
        if (opIndex == -1)
            return new BigDecimal(expr);
        char c = expr.charAt(opIndex);

        if(opIndex > 0){
            BigDecimal val1 = new BigDecimal(expr.substring(0,opIndex));
            BigDecimal val2 = new BigDecimal(expr.substring(opIndex+1));
            System.out.println("Valor 1 = "+val1.toString() + " Valor 2 = "+val2.toString());

            if(c == '+')
                return val1.add(val2);
            if(c=='-')
                return val1.subtract(val2);
            if(c=='x')
                return val1.multiply(val2);
            if(c=='/')
                return val1.divide(val2, 5, RoundingMode.CEILING);
        }
        if(opIndex == 0) {
            //TODO: se der tempo suportar expressões
            if ( c == '+' || c == '-')
                return new BigDecimal(expr);
        }
        else
            throw new Exception("Expressão inválida");

        return result;
    }

    public void clickResult(View v){
        //tratar primeiro multiplicação e divisão
        //subtração e adição podem ser feitas a primeira que achar
        String expr = edtExpression.getText().toString();

        try {
            //TODO: aqui ocorre a mágica
            BigDecimal resultado = getResult(expr);
            edtExpression.setText(resultado.toString());
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