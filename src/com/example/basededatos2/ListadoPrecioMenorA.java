package com.example.basededatos2;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ListadoPrecioMenorA extends Activity {
	ListView lv1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_precio_menor);
		lv1=(ListView)findViewById(R.id.listView1);
		
		Bundle datos=getIntent().getExtras();
		float precio=Float.parseFloat(datos.getString("precio"));
		
		cargarAutos(precio);
	}

	
	private void cargarAutos(float precio) {
		ArrayList<String>lista1=new ArrayList<String>();
		
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "base1", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		Cursor registro = bd.rawQuery("select patente,marca,modelo,precio from autos where precio<"+precio, null);
		
		while (registro.moveToNext())
		{
			lista1.add("Patente: " + registro.getString(0) +"\n"+
					"Marca: " + registro.getString(1) +"\n"+
					"Modelo: " + registro.getString(2) +"\n"+
					"Precio: " + registro.getString(3));
		}
		if (lista1.size()==0) {
			Toast.makeText(this, "No hay vehiculos con un precio inferior a $"+precio, Toast.LENGTH_LONG).show();
		}
		ArrayAdapter<String>adaptador1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista1);
		lv1.setAdapter(adaptador1);		
		
	}


	public void retornar (View v) {
		finish();
	}

}
