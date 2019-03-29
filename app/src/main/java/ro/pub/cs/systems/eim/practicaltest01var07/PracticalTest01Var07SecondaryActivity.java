package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    private EditText nr00 = null;
    private EditText nr01 = null;
    private EditText nr10 = null;
    private EditText nr11 = null;

    private Button sum = null;
    private Button product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        nr00 = findViewById(R.id.nr00);
        nr01 = findViewById(R.id.nr01);
        nr10 = findViewById(R.id.nr10);
        nr11 = findViewById(R.id.nr11);
        sum = findViewById(R.id.sum);
        product = findViewById(R.id.product);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra("nr00")) {
                nr00.setText(String.valueOf(intent.getIntExtra("nr00", -1)));
            }
            if (intent.hasExtra("nr01")) {
                nr01.setText(String.valueOf(intent.getIntExtra("nr01", -1)));
            }
            if (intent.hasExtra("nr10")) {
                nr10.setText(String.valueOf(intent.getIntExtra("nr10", -1)));
            }
            if (intent.hasExtra("nr11")) {
                nr11.setText(String.valueOf(intent.getIntExtra("nr11", -1)));
            }
        }

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int nr0 = Integer.valueOf(nr00.getText().toString());
                int nr1 = Integer.valueOf(nr01.getText().toString());
                int nr2 = Integer.valueOf(nr10.getText().toString());
                int nr3 = Integer.valueOf(nr11.getText().toString());


                switch (v.getId()) {
                    case R.id.sum:
                        Toast.makeText(getApplicationContext(), "Sum: " + (nr0 + nr1 + nr2 + nr3), Toast.LENGTH_LONG).show();

                        break;
                    case R.id.product:
                        Toast.makeText(getApplicationContext(), "Product: " + (nr0 * nr1 * nr2 * nr3), Toast.LENGTH_LONG).show();

                        break;
                    default:
                        break;
                }
            }
        };

        sum.setOnClickListener(listener);
        product.setOnClickListener(listener);
    }
}
