package com.example.consulprecios;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.tscdll.TSCActivity;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity  {

    TSCActivity TscDll = new TSCActivity();


    private EditText Cod, edt_cantidad;
    private Button Consul,imprimir,mostrarC;
    private TextView Descripcion, Precio, Codigo;
    String codigo;
    Statement stn=conecta().createStatement();;
    AlertDialog mdialog;

    public MainActivity() throws SQLException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);





        Cod = (EditText) findViewById(R.id.txt1);
        Cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarProducto();
                Cod.setText("");
            }
        });





        Codigo = (TextView) findViewById(R.id.lbc);
        Descripcion = (TextView) findViewById(R.id.lbd);
        edt_cantidad = (EditText) findViewById(R.id.edt_cantidad);
        Consul= (Button) findViewById(R.id.buttonC);
        mostrarC= (Button)findViewById(R.id.btn_mostarC);
        mostrarC= (Button)findViewById(R.id.btn_mostarC);

        mostrarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ActividadMostrar.class);
                startActivity(intent);
                finish();
            }
        });



        Consul.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ConsultarProducto();
                Cod.setText("");
            }
        });


        imprimir = (Button) findViewById(R.id.buttonI);
        imprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imprimir();
                edt_cantidad.setText("");
            }
        });

    }


    public Connection conecta() {

   Connection cnn=null;

           try {
               StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
               StrictMode.setThreadPolicy(politica);

               Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
               cnn = DriverManager.getConnection (""
                       + "jdbc:jtds:sqlserver://192.168.0.241:1433/INDICO;instance=SQL2008;"
                       + "user=Programacion;password=Ej8246130");




           } catch (Exception e) {
               Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
           }




        return cnn;




    }

    public void ConsultarProducto() {


        try {


            ResultSet rs = stn.executeQuery("SELECT  cod_item,des_item FROM dbo.inv_items WHERE cod_item='"+Cod.getText().toString() + "'");

            if(rs.next()) {
                Codigo.setText(rs.getString(1));
                Descripcion.setText(rs.getString(2));



            }else{

                Toast.makeText(this,"Este codigo no se encuetra registrado",Toast.LENGTH_SHORT).show();
            }

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();


        }


    }

    public void Imprimir() {


     String edt= edt_cantidad.getText().toString();
     String codigo= Codigo.getText().toString();
     String descrip= Descripcion.getText().toString();
     ArrayList arr= new ArrayList();
     String prmerP = "",segundoP="",tercerP="", cuartop="";


     int cantidad= Integer.parseInt(edt);

     for(int i=0;i< descrip.length(); i++){
         if(i<=23){
            prmerP+= descrip.charAt(i);

         }else if(i>=24 && i<= 46){

             segundoP+= descrip.charAt(i);
         }else if(i>=47 && i<=69){

             tercerP+= descrip.charAt(i);
         }else if (i>=70 && i<=92){
             cuartop+= descrip.charAt(i);

         }

     }


        //// Configuracion Impresora ////
        TscDll.openport("00:0C:BF:13:33:7E");
        TscDll.setup(50, 25, 4, 4, 0, 3, 0);

        TscDll.clearbuffer();
        //// texto fijo///


        //// Variables ////

        /*TscDll.sendcommand("TEXT 10,40,\"ROMAN.TTF\",0,8,8,\"Codigo:  \"\n");
        TscDll.printerfont(20, 40, "2", 0, 1, 1,"    "+codigo);

        TscDll.sendcommand("TEXT 10,65,\"ROMAN.TTF\",0,1,8,\"Desc: \"\n");
        TscDll.printerfont(5, 65, "2", 0, 1, 1,"    "+prmerP);
        TscDll.printerfont(5, 90, "2", 0, 1, 1,"    "+segundoP);
        TscDll.printerfont(5, 115, "2", 0, 1, 1,"    "+tercerP);
        TscDll.printerfont(5, 140, "2", 0, 1, 1,"    "+cuartop);*/

        TscDll.sendcommand("QRCODE 349,194,L,3,A,180,M2,S7,\"252197-452\"\n");
        TscDll.sendcommand("BLOCK 5,65,390,300,\"0\",0,9,9,3,1,\"ygyuguytjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkk\"\n");


        TscDll.printlabel(1, cantidad);

        TscDll.closeport();

        if (findViewById(R.id.edt_cantidad).isFocusable()) {
            findViewById(R.id.txt1).requestFocus();
        }








    }






}










