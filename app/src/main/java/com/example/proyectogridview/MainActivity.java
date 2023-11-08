package com.example.proyectogridview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declarar variables
    Spinner spSpinner;
    String[] comunas = new String[]{"Puente Alto", "Macul", "San Miguel", "Lampa", "La Florida"};
    EditText edtRut, edtNom, edtDir;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defino los campos del formulario
        edtRut = (EditText) findViewById(R.id.edtRut);
        edtNom = (EditText) findViewById(R.id.edtNom);
        edtDir = (EditText) findViewById(R.id.edtDir);
        spSpinner = (Spinner) findViewById(R.id.spSpinner);
        lista = (ListView) findViewById(R.id.lstLista);

        //Poblar Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, comunas);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(spinnerAdapter);
        CargarLista();
    }

    public void onClickAgregar(View view){
        DataHelper dh=new DataHelper(this,"alumno.db",null, 1);
        SQLiteDatabase bd= dh.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("rut",edtRut.getText().toString());
        reg.put("nom",edtNom.getText().toString());
        reg.put("dir",edtDir.getText().toString());
        reg.put("com", spSpinner.getSelectedItem().toString());
        long resp = bd.insert("alumno", null, reg);
        bd.close();
        if (resp==-1){
            Toast.makeText(this,"No se puede ingresar el registro", Toast.LENGTH_LONG).show();
        }
        else {Toast.makeText(this,"Registro Agregado",Toast.LENGTH_LONG).show();}
        CargarLista();
    }

    public void onClickEliminar(View view){
        DataHelper dh=new DataHelper(this, "alumno.db",null,1);
        SQLiteDatabase bd= dh.getWritableDatabase();
        String bRut=edtRut.getText().toString();
        long resp = bd.delete("alumno","rut=" + bRut, null);
        bd.close();
        if (resp==-1){
            Toast.makeText(this,"Registro no encontrado",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Registro eliminado",Toast.LENGTH_LONG).show();
        }
        CargarLista();
    }

    public void CargarLista(){
        DataHelper dh = new DataHelper(this, "alumno.db", null, 1);
        SQLiteDatabase bd = dh.getReadableDatabase();
        Cursor c = bd.rawQuery("Select rut, nom, dir, com from alumno", null);
        String[] arr = new String[c.getCount()];
        if (c.moveToFirst() == true) {
            int i = 0;
            do {
                String linea = "||"+ c.getInt(0) + "||" + c.getString(1)
                        + "||" + c.getString(2) + "||" + c.getString(3) + "||";
                arr[i] = linea;
                i++;
            } while (c.moveToNext() == true);
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, arr);
            lista.setAdapter(adaptador);
            bd.close();
        }
    }
}