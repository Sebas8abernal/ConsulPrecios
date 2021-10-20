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

public class LoginActivity extends AppCompatActivity {

    EditText usuario, contraseña;
    Button btn_iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario= findViewById(R.id.edt_usuarioIniciarSesion);
        contraseña= findViewById(R.id.edt_contraIniciarSesion);
        btn_iniciar= findViewById(R.id.btn_login);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario1 = usuario.getText().toString();
                String contraseña1= contraseña.getText().toString();

                Response.Listener<String> listener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success= jsonObject.getBoolean("success");
                            if(success){
                                Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }else{

                                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                };

                LoginRequest loginActivity= new LoginRequest(usuario1,contraseña1,listener);
                RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginActivity);


            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(LoginActivity.this,ActivityPrincipal.class);

        startActivity(intent);
        finish();
    }
}
