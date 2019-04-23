package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent nextActivity;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confPass;
    private Button next;
    private TextView erorrName;
    private TextView erorrPass;
    private TextView erorrEmail;
    private TextView erorrConfPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nextActivity=new Intent(this,MainActivity.class);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        confPass=(EditText)findViewById(R.id.conf_password);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        erorrName=findViewById(R.id.erorr_name);
        erorrEmail=findViewById(R.id.erorr_email);
        erorrPass=findViewById(R.id.erorr_pass);
        erorrConfPass=findViewById(R.id.erorr_conf_pass);






    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                register();
                break;
            default:
                break;
        }

    }
    public void register() {
        Log.d("mytag", "This is register");
        String nameR=name.getText().toString();
        String emailR=email.getText().toString();
        String passR=password.getText().toString();
        String passConfR=confPass.getText().toString();
        erorrName.setVisibility(View.INVISIBLE);
        erorrEmail.setVisibility(View.INVISIBLE);
        erorrPass.setVisibility(View.INVISIBLE);
        erorrConfPass.setVisibility(View.INVISIBLE);


            if(nameR.isEmpty()){

                erorrName.setVisibility(View.VISIBLE);
                erorrName.setText("Type your name please");

            }
            if(emailR.isEmpty()){
                erorrEmail.setVisibility(View.VISIBLE);
                erorrEmail.setText("Type your email please");
            }
            if(passR.isEmpty()){
                erorrPass.setVisibility(View.VISIBLE);
                erorrPass.setText("Type password please");
            }
            if(passConfR.isEmpty()){
                erorrConfPass.setVisibility(View.VISIBLE);
                erorrConfPass.setText("Confirm password please");
            }
            if (isEmailValid(emailR)){
                if(passR.equals(passConfR))
                {
                    //your code to register the user like an entry in the database
                    SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("NAME",nameR);
                    editor.putString("EMAIL",emailR);
                    editor.putString("PASSWORD",passR);
                    editor.putBoolean("ISLOGGEDIN",true);
                    editor.commit();
                    startActivity(nextActivity);
                } else{
                    erorrConfPass.setText("Password not mutchs");
                    erorrConfPass.setText("Password not mutchs");
                }

            }
            else{
                erorrEmail.setVisibility(View.VISIBLE);
                erorrEmail.setText("Invalid Email");
            }


    }

    public final static boolean isEmailValid(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
