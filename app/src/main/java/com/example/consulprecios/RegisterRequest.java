package com.example.consulprecios;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="https://sebasindico2021.000webhostapp.com/register.php";
    private Map<String, String> params;

    public  RegisterRequest(String usu, String contra, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,null);
        params= new HashMap<>();
        params.put("user",usu);
        params.put("pass",contra);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
