package com.example.dell.miniproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    regis reg;
    private FirebaseAuth auth;
    Button b1;
    public EditText oname,oflatno, oemail, opassowrd, ocontact, odetails;
    String name, flatno, email,password, contact, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
  auth = FirebaseAuth.getInstance();

        oname = (EditText) findViewById(R.id.ownername);
        oflatno = (EditText) findViewById(R.id.ownerflatno);
        oemail = (EditText) findViewById(R.id.owneremail);
        opassowrd = (EditText) findViewById(R.id.ownerpassword);
        ocontact = (EditText) findViewById(R.id.ownercontact);
        odetails = (EditText) findViewById(R.id.ownerdetails);
        b1 = (Button) findViewById(R.id.signup_owner);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("regis");
        reg = new regis(R.drawable.usergreen);

    }
    private void getValues(){

        name = oname.getText().toString().trim();
        flatno = oflatno.getText().toString().trim();
        email=oemail.getText().toString().trim();
        password = opassowrd.getText().toString().trim();
        contact = ocontact.getText().toString().trim();
        details = odetails.getText().toString().trim();

        reg.setname(oname.getText().toString());
        reg.setflatno(oflatno.getText().toString());
        reg.setemail(oemail.getText().toString());
        reg.setpassword(opassowrd.getText().toString());
        reg.setcontact(ocontact.getText().toString());
        reg.setdetails(odetails.getText().toString());
    }


    public  void btnInsert(View view) {

        getValues();
        DatabaseReference newRef = ref.child("Owners");
        newRef.push().setValue(reg);
        Toast.makeText(RegistrationActivity.this, "Registration Successful ! ", Toast.LENGTH_LONG).show();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Enter your Name!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(flatno)) {
            Toast.makeText(getApplicationContext(), "Enter your Flat No!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter your Email ID!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter your Password!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(contact)) {
            Toast.makeText(getApplicationContext(), "Enter your Contact No!", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (TextUtils.isEmpty(details)) {
            Toast.makeText(getApplicationContext(), "Enter Family Details!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegistrationActivity.this, "You are successfully registered owner now !!" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                         if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(RegistrationActivity.this, Login_owner.class));
                            finish();
                        }
                    }
                });
    }
}
