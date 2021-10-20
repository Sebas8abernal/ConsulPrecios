package com.example.consulprecios;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="https://sebasindico2021.000webhostapp.com/login.php";
    private Map<String, String> params;
    public LoginRequest(String username, String password, Response.Listener <String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,null);
        params= new HashMap<>();
        params.put("user",username);
        params.put("pass",password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
