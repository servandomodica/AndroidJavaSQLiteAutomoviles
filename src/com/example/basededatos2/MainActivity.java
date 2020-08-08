package com.example.basededatos2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

//IMPORTANTE: PRIMERO, EN SRC, MISMO PAQUETE, CREO NEW, CLASS, TIPO SQLiteOpenHelper
//En browse, hereda de android.database.sqlite.SQLiteOpenHelper

public class MainActivity extends Activity {
	EditText et1,et2,et3,et4,et5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		et4 = (EditText) findViewById(R.id.editText4);
		et5 = (EditText) findViewById(R.id.editText5);
    }

    
    public void agregar (View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "base1", null, 1); // BASE1 ES EL NOMBRE DE LA BASE DE DATOS QUE SE
							// CREARA EN DATA / DATA / COM... / DATABASES
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		ContentValues registro = new ContentValues();
		registro.put("patente", et1.getText().toString());
		registro.put("marca", et2.getText().toString());
		registro.put("modelo", et3.getText().toString());
		registro.put("precio", et4.getText().toString());
		
		bd.insert("autos", null, registro);		
		bd.close();
		
		et1.setText("");
		et2.setText("");
		et3.setText("");
		et4.setText("");
		Toast.makeText(this, "Se almacenó el auto correctamente", Toast.LENGTH_LONG).show();
    }
        
    
    public void mostrar (View v) {    	
    	Intent intento1=new Intent(this, ListadoPrecioMenorA.class);
    	intento1.putExtra("precio", et5.getText().toString());
    	startActivity(intento1);
    	et5.setText("");
    }
    
    
    public void listadoCompleto (View v) {
    	Intent intento1=new Intent(this, ListadoCompleto.class);
    	startActivity(intento1);    	
    }
    
}
