package com.example.anuragshinde.grade;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Error;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Error = (TextView)findViewById(R.id.txtView);
        signUp = (TextView)findViewById(R.id.signUp) ;

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Error.setText("");
                authenticate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }




    private void authenticate(String userName, String userPassword){

        Database database = new Database(this);
        SQLiteDatabase db = database.getReadableDatabase();
        String []columns = {"username", "password"};
        String [] values = {userName,userPassword};

        Cursor cursor = db.query("User", columns, "username = ? AND password = ?", values,null,null,null);
        if(cursor.moveToFirst()){
             if(userName.equals(cursor.getString(cursor.getColumnIndex("username")))  && userPassword.equals(cursor.getString(cursor.getColumnIndex("password"))) ){
                 Toast.makeText(this,"Login successful!!",Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                 intent.putExtra("username", userName);
                 startActivity(intent);

            }

        } else{
            Toast.makeText(this,"Login failed!!",Toast.LENGTH_LONG).show();
        }
    }

}
