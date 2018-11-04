package com.example.dell.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_accountant extends AppCompatActivity {

    private static EditText username;
    private static EditText password;
    // private static TextView attempts;
    private static Button btn;
    private int counter = 3;
    //public static String msg = "hie Pooja !!";
    //public static String uname = "Pooja !!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_accountant);

        username= (EditText) findViewById(R.id.acc_email);
        password = (EditText) findViewById(R.id.acc_password);
        //   attempts = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.signin_acc);
        // attempts.setText("Number of attempts : 5");

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Validate(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void Validate(String name, String pass) {

        if (name.equals("account@gmail.com") && pass.equals("1234567")) {
            Intent in = new Intent(Login_accountant.this, MainActivity_Accountant.class);
            String message = username.getText().toString();
            // in.putExtra(uname, message);
            startActivity(in);
        }

        else
        {
            counter--;
            Toast t = Toast.makeText(getApplicationContext(), "Incorrect Details ! Attempts remained:  " + String.valueOf(counter), Toast.LENGTH_LONG);
            t.show();
            //attempts.setText("attempts remaining : "+String.valueOf(counter));
            if(counter==0)
            {
                btn.setEnabled(false);
                Toast t1 = Toast.makeText(getApplicationContext(), "Login Failed " + String.valueOf(counter), Toast.LENGTH_LONG);
                t1.show();
            }
        }
    }
}

