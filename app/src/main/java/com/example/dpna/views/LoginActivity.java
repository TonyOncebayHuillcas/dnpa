package com.example.dpna.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dpna.ClassTask.LoginTask;
import com.example.dpna.R;
import com.example.dpna.Utils.ConnectionDetector;
import com.example.dpna.config.ConstValue;

public class LoginActivity extends AppCompatActivity {
    Context context;
    Activity activity;
    public ConnectionDetector cd;
    Button btn_login;
    EditText et_username,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;
        activity=this;

        cd = new ConnectionDetector(context);
        et_username= (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, InitActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                /*
                if (cd.isConnectingToInternet()) {
                    if(et_username.getText().toString().length()==0){
                        et_username.setError("Ingrese Usuario");
                    }else if (et_password.getText().toString().length()==0){
                        et_password.setError("Ingrese Contraseña");
                    } else {
                        ConstValue.setUsername(et_username.getText().toString());
                        ConstValue.setPassword(et_password.getText().toString());
                        ConstValue.setAccept(false);
                        new LoginTask().execute(true);
                        if(ConstValue.getAccept()){
                            Intent intent = new Intent(LoginActivity.this, InitActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "BIKA - Usuario y/o contraseña incorrecta, porfavor intente nuevamente.", Toast.LENGTH_LONG).show();
                        }

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "CloudTMP - Su dispositivo no cuenta con conexión a internet.", Toast.LENGTH_LONG).show();
                }*/

            }

        });

    }
}
