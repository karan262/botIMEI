package com.example.botimei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //declare objects
    EditText email, password;
//    LinearLayout logoImg;
//    ImageButton btnIn;//signInButton
//    Animation spls_fade;
//    TextView signUpLink;

    private FirebaseAuth mAuth;//declare fireBaseAuth object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        Button login;
        login = findViewById(R.id.btnSignin);

//store value
        //spls_fade = AnimationUtils.loadAnimation(this,R.anim.spls_fade);
        email = (EditText) findViewById(R.id.signInUserName);
        password = (EditText) findViewById(R.id.signInPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()){
                    email.setError("Username is Required");
                }
                if (password.getText().toString().isEmpty()){
                    password.setError("Password is Required");
                }
                /*if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "All fields must be filled", Toast.LENGTH_LONG).show();*/
                else if (password.getText().toString().length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password must be 8 characters long", Toast.LENGTH_LONG).show();
                } else {
                    checkLogin(email.getText().toString(), password.getText().toString());
                }
            }
        });
    }
    //login validation
    private void checkLogin(String e, String p)
    {
        mAuth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG","Hii there my value is"+task.getResult());
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Welcome"+email+"\nYou are logged in",Toast.LENGTH_LONG).show();

                            Intent i;
                            i = new Intent(getApplicationContext(), TestActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login Failed\n Check your username or password",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    //redirect signup page event
//    public void signUp(View view)
//    {
//        Intent signUpIntentObj = new Intent(getApplicationContext(),SignupActivity.class);
//        startActivity(signUpIntentObj);
//    }
}
