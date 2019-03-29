package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    private Button set = null;
    private EditText nr00 = null;
    private EditText nr01 = null;
    private EditText nr10 = null;
    private EditText nr11 = null;

    private IntentFilter intentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        set = findViewById(R.id.set);
        nr00 = findViewById(R.id.nr00);
        nr01 = findViewById(R.id.nr01);
        nr10 = findViewById(R.id.nr10);
        nr11 = findViewById(R.id.nr11);


        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nr0 = nr00.getText().toString();
                String nr1 = nr01.getText().toString();
                String nr2 = nr10.getText().toString();
                String nr3 = nr11.getText().toString();

                if (!nr0.isEmpty() && !nr1.isEmpty() && !nr2.isEmpty() && !nr3.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                    int inr0 = Integer.valueOf(nr0);
                    int inr1 = Integer.valueOf(nr1);
                    int inr2 = Integer.valueOf(nr2);
                    int inr3 = Integer.valueOf(nr3);

                    intent.putExtra("nr00", inr0);
                    intent.putExtra("nr01", inr1);
                    intent.putExtra("nr10", inr2);
                    intent.putExtra("nr11", inr3);

                    startActivity(intent);
                }
            }
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("nr00")) {
                nr00.setText(savedInstanceState.getString("nr00"));
            }
            if (savedInstanceState.containsKey("nr01")) {
                nr01.setText(savedInstanceState.getString("nr01"));
            }
            if (savedInstanceState.containsKey("nr10")) {
                nr10.setText(savedInstanceState.getString("nr10"));
            }
            if (savedInstanceState.containsKey("nr11")) {
                nr11.setText(savedInstanceState.getString("nr11"));
            }
        }

        Intent serviceIntent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
        getApplicationContext().startService(serviceIntent);

        intentFilter = new IntentFilter();
        intentFilter.addAction("ro.pub.cs.systems.eim.arraylist");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("nr00", nr00.getText().toString());
        savedInstanceState.putString("nr01", nr01.getText().toString());
        savedInstanceState.putString("nr10", nr10.getText().toString());
        savedInstanceState.putString("nr11", nr11.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("nr00")) {
            nr00.setText(savedInstanceState.getString("nr00"));
        }
        if (savedInstanceState.containsKey("nr01")) {
            nr01.setText(savedInstanceState.getString("nr01"));
        }
        if (savedInstanceState.containsKey("nr10")) {
            nr10.setText(savedInstanceState.getString("nr10"));
        }
        if (savedInstanceState.containsKey("nr11")) {
            nr11.setText(savedInstanceState.getString("nr11"));
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("aici", "onReceive: ");
            if (intent.hasExtra("msg")) {
                int[] arr = intent.getIntArrayExtra("msg");

                nr00.setText(String.valueOf(arr[0]));
                nr01.setText(String.valueOf(arr[1]));
                nr10.setText(String.valueOf(arr[2]));
                nr11.setText(String.valueOf(arr[3]));
            }
        }
    }
}
