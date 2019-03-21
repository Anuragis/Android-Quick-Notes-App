package com.example.anuragshinde.grade;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


    private EditText username, password, email;
    private Button signUp;
    private TextView loginInLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);
        email = (EditText)findViewById(R.id.etEmail);
        loginInLink = (TextView)findViewById(R.id.etLink);
        signUp = (Button)findViewById(R.id.SignUp);



        final Database userDb = new Database(this);
        final SQLiteDatabase db = userDb.getWritableDatabase();
        final ContentValues  values = new ContentValues();

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isValid()){
                    values.put("username",username.getText().toString());
                    values.put("password", password.getText().toString());
                    values.put("email", email.getText().toString());

                    db.insert("User", null,values);
                    Toast.makeText(SignUpActivity.this, "Registeration Complete!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                }
            }
        });

        loginInLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               startActivity(new Intent(SignUpActivity.this,MainActivity.class));
            }
        });


    }


    private Boolean isValid(){
        Boolean isValid = true;
        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
            Toast.makeText(this, "Registeration Details incomplete!!", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return  isValid;
    }
}
