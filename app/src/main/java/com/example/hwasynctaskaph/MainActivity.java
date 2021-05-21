package com.example.hwasynctaskaph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//variables
    private TextView mtxtv_1;
    private ProgressBar mpb_1;
    private static final String TEXT_STATE = "CurrentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtxtv_1 = findViewById(R.id.txt1);
        mpb_1 = findViewById(R.id.pb1);
//duardando los datos, si se interrumpe el ciclo de vida de la activity
        if (savedInstanceState != null){
            mtxtv_1.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    /**
     * Método del onclick
     * @param view recibe la vista de donde fue llamado
     */
    public void startTask(View view) {
        mtxtv_1.setText(R.string.napping);
        new SimpleAsyncTask(mtxtv_1, mpb_1).execute();
    }

    /**
     * método que guadara el valor de el textview
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,
                mtxtv_1.getText().toString());
    }
}