package com.example.sob_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class home extends AppCompatActivity {
    TextView name, phone, email, dob, password, kin, gender, qualificaton, status, state;
    String fname, pnum, em, date, next, pword, sex, education, origin, relationship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        password = findViewById(R.id.password);
        kin = findViewById(R.id.kin);
        gender = findViewById(R.id.gender);
        qualificaton = findViewById(R.id.qualification);
        status = findViewById(R.id.status);
        state = findViewById(R.id.state);

        Intent intent = getIntent();
        fname = intent.getStringExtra(register.EXTRA_TEXT1);
        pnum = intent.getStringExtra(register.EXTRA_TEXT2);
        em = intent.getStringExtra(register.EXTRA_TEXT3);
        date = intent.getStringExtra(register.EXTRA_TEXT4);
        pword = intent.getStringExtra(register.EXTRA_TEXT6);
        next = intent.getStringExtra(register.EXTRA_TEXT5);
        sex = intent.getStringExtra(register.EXTRA_TEXT7);
        education = intent.getStringExtra(register.EXTRA_TEXT8);
        origin = intent.getStringExtra(register.EXTRA_TEXT9);
        relationship = intent.getStringExtra(register.EXTRA_TEXT10);

        name.setText(fname);
        phone.setText(pnum);
        email.setText(em);
        dob.setText(date);
        password.setText(pword);
        kin.setText(next);
        gender.setText(sex);
        qualificaton.setText(education);
        status.setText(relationship);
        state.setText(origin);
    }
}
