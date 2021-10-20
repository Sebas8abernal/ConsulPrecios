package com.example.consulprecios;

import android.os.StrictMode;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActividadMostrar extends AppCompatActivity {
        ImageView btn_image;
        TextView btn_text;
        int i=0;
    LinearLayout llBotonera;
    LinearLayout.LayoutParams lp;
    int numBotones = 20;
    Statement stn=conecta().createStatement();;

    public ActividadMostrar() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mostrar);

        //Obtenemos el linear layout donde colocar los botones
        llBotonera = (LinearLayout) findViewById(R.id.llBotonera);

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
         lp= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );
          query();


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

   public void query(){
        try {


        ResultSet rs = stn.executeQuery("SELECT  cod_item,des_item FROM dbo.inv_items");

        while(rs.next()) {
            i++;
            Button button = new Button(this);

            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);


            //Asignamos Texto al botón
            button.setText(rs.getString(1));


            //Añadimos el botón a la botonera
            llBotonera.addView(button);

        }


    } catch (SQLException ex) {
        ex.printStackTrace();


    }

   }
}
