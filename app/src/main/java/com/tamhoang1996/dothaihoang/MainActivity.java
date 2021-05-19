package com.tamhoang1996.dothaihoang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tamhoang1996.dothaihoang.model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edName,edEmail,edDescription;
    CheckBox checkBox;
    Spinner spinner;
    Button btSend;

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.getAppDatabase(this);
        initView();
    }

    private void initView(){
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edDescription = findViewById(R.id.edDescription);
        checkBox =findViewById(R.id.ck);
        btSend = findViewById(R.id.btSend);
        btSend.setOnClickListener(this);

        String [] gripes = {"A","B","C"};
        spinner =findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,gripes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addFeedback(){
        if (edName.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter name",Toast.LENGTH_LONG).show();
            return;
        }
        if (edEmail.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (edDescription.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter description",Toast.LENGTH_LONG).show();
            return;
        }
        if (spinner.getSelectedItem().toString().isEmpty()){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (!checkBox.isChecked()){
            Toast.makeText(this,"Please checkbox",Toast.LENGTH_LONG).show();
            return;
        }
        Feedback feedback = new Feedback();
        feedback.name = edName.getText().toString();
        feedback.email = edEmail.getText().toString();
        feedback.description = edDescription.getText().toString();
        feedback.gripe =spinner.getSelectedItem().toString();

        appDatabase.feedbackDao().insertFeedback(feedback);


        List<Feedback> list = new ArrayList<>();
        list = appDatabase.feedbackDao().getAllUser();

        Log.d("TAG", "insertDb: "+list.size());
        Toast.makeText(this,list.size() + " Record",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onClick(View v) {
        if (v == btSend){
            addFeedback();
        }
    }
}