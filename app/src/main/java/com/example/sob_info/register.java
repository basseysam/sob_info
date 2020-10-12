package com.example.sob_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class register extends AppCompatActivity {
 Button register;
 EditText fullname, phone, email, dob, next, password;
 Spinner genderlist, qualificationlist, statelist, statuslist;
 String fname, pnum, em, date, kin, pword, gender, qualification, state, status;
 public static final String EXTRA_TEXT1 = "com.example.sob_info1";
    public static final String EXTRA_TEXT2 = "com.example.sob_info2";
    public static final String EXTRA_TEXT3 = "com.example.sob_info3";
    public static final String EXTRA_TEXT4 = "com.example.sob_info4";
    public static final String EXTRA_TEXT5 = "com.example.sob_info5";
    public static final String EXTRA_TEXT6 = "com.example.sob_info6";
    public static final String EXTRA_TEXT7 = "com.example.sob_info7";
    public static final String EXTRA_TEXT8 = "com.example.sob_info8";
    public static final String EXTRA_TEXT9 = "com.example.sob_info9";
    public static final String EXTRA_TEXT10 = "com.example.sob_info10";
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register);
        fullname = findViewById(R.id.fullname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        next = findViewById(R.id.next);
        password = findViewById(R.id.password);
        genderlist = findViewById(R.id.gender);
        qualificationlist = findViewById(R.id.qualification);
        statelist = findViewById(R.id.state);
        statuslist = findViewById(R.id.status);
        helper = new DatabaseHelper(getApplicationContext());

        List<String> glist = new ArrayList<>();
        glist.add("--GENDER--");
        glist.add("Male");
        glist.add("Female");
        ArrayAdapter<String> gadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, glist);
        genderlist.setAdapter(gadapter);



        List<String> qlist = new ArrayList<>();
        qlist.add("--QUALIFICATION--");
        qlist.add("B.sc");
        qlist.add("HND");
        qlist.add("FSLC");
        qlist.add("M.sc");
        qlist.add("Ph.D");
        qlist.add("SSCE");
        qlist.add("PGD");
        qlist.add("OND");
        ArrayAdapter<String> qadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qlist);
        qualificationlist.setAdapter(qadapter);


        List<String> slist = new ArrayList<>();
        slist.add("--STATE OF ORIGIN--");
        slist.add("Cross River");
        slist.add("Akwa Ibom");
        slist.add("Lagos");
        slist.add("Ondo");
        slist.add("Osun");
        slist.add("Abia");
        slist.add("Benue");
        slist.add("Ebonyi");
        ArrayAdapter<String> sadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, slist);
        statelist.setAdapter(sadapter);


        List<String> stlist = new ArrayList<>();
        stlist.add("--STATUS--");
        stlist.add("Married");
        stlist.add("Single");
        stlist.add("Divorced");
        stlist.add("Widow");
        stlist.add("Widower");
        ArrayAdapter<String> stadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stlist);
        statuslist.setAdapter(stadapter);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname = fullname.getText().toString();
                pnum = phone.getText().toString();
                em = email.getText().toString();
                date = dob.getText().toString();
                kin = next.getText().toString();
                pword = password.getText().toString();
                gender = genderlist.getSelectedItem().toString();
                qualification = qualificationlist.getSelectedItem().toString();
                state = statelist.getSelectedItem().toString();
                status = statuslist.getSelectedItem().toString();

                if(fname.isEmpty() || pnum.isEmpty() || em.isEmpty() || date.isEmpty() || kin.isEmpty() || pword.isEmpty() || "--GENDER--".equalsIgnoreCase(gender)
                        || "--QUALIFICATION--".equalsIgnoreCase(qualification) || "--STATE OF ORIGIN--".equalsIgnoreCase(state) || "--STATUS--".equalsIgnoreCase(status)){

                    Toast.makeText(getApplicationContext(), "No Fields Must Be Empty", Toast.LENGTH_SHORT).show();
                }else{

                    pword = encrypt(pword);
                    Intent intent = new Intent(getApplicationContext(), home.class);
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(EXTRA_TEXT1, fname);
                    intent.putExtra(EXTRA_TEXT2, pnum);
                    intent.putExtra(EXTRA_TEXT3, em);
                    intent.putExtra(EXTRA_TEXT4, date);
                    intent.putExtra(EXTRA_TEXT5, kin);
                    intent.putExtra(EXTRA_TEXT6, pword);
                    intent.putExtra(EXTRA_TEXT7, gender);
                    intent.putExtra(EXTRA_TEXT8, qualification);
                    intent.putExtra(EXTRA_TEXT9, state);
                    intent.putExtra(EXTRA_TEXT10, status);


                    boolean res = helper.insertIntoUserTable(fname, pnum, em, date, kin, pword, gender, qualification, state, status);
                    if(res){
                        Toast.makeText(getApplicationContext(), "SUCCESSFULLY INSERTED", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "UNABLE TO INSERT\nPHONE NUMBER ALREADY\nEXIST IN DATABASE", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public String encrypt(String data){

        char[] original = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String key[] = {"A02b4", "c2f36", "mzqrP", "40268", "sSxpY", "3?-;!", "\n03f1", "zQA?/","01024", "f6f9c"};
        String newPassword = "";
        int len = data.length();
        for(int i = 0; i<len; i++){
            for(int j = 0; j<10; j++){
                if(data.charAt(i) == original[j]){
                   newPassword = newPassword + key[j];
                   break;
                }
            }

        }
        return newPassword;
    }
}
