package com.example.consulprecios;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ActivityPrincipal extends AppCompatActivity {


    AlertDialog mdialog;

    public ActivityPrincipal() throws SQLException {
    }


    Button btn_reg, btn_log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btn_reg = findViewById(R.id.btn_reg);
        btn_log = findViewById(R.id.btn_log);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincipal.this, ActivityRegister.class);

                startActivity(intent);
                finish();
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincipal.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



}

