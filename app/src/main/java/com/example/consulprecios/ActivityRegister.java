package com.example.consulprecios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ResponseCache;

public class ActivityRegister extends AppCompatActivity {

    EditText edt_usu, edt_contra1,edt_contra2;
    Button btn_regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_usu= findViewById(R.id.edt_usuario);
        edt_contra1= findViewById(R.id.edt_contra1);
        edt_contra2= findViewById(R.id.edt_contra2);

        btn_regis= findViewById(R.id.btn_registrarse);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario= edt_usu.getText().toString();
                String contra1= edt_contra1.getText().toString();
                String contra2= edt_contra2.getText().toString();

                if(!contra1.equals(contra2)){

                    Toast.makeText(ActivityRegister.this, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_SHORT).show();
                }else{

                    Response.Listener<String> respoListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {


                                JSONObject jsonObject = new JSONObject(response);
                                boolean success= jsonObject.getBoolean("success");

                                if(success){

                                    Intent intent= new Intent(ActivityRegister.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                }else{
                                    Toast.makeText(ActivityRegister.this, "error", Toast.LENGTH_LONG).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }




                        }
                    };

                    RegisterRequest registerRequest= new RegisterRequest(usuario,contra1,respoListener );
                    RequestQueue queue= Volley.newRequestQueue(ActivityRegister.this);
                    queue.add(registerRequest);


                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(ActivityRegister.this,ActivityPrincipal.class);

        startActivity(intent);
        finish();
    }
}
