package com.example.proyectogridview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class Consulta extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        CargarLista();
    }

    public void CargarLista(){
        DataHelper dh = new DataHelper(this, "alumno.db", null, 1);
        SQLiteDatabase bd = dh.getReadableDatabase();
        Cursor c = bd.rawQuery("Select rut, nom, dir, com, imagen from alumno", null);

        ArrayList<Alumno> alumnos = new ArrayList<>();
        if (c.moveToFirst()){
            do
            {
                int rut = c.getInt(0);
                String nombre = c.getString(1);
                String direccion = c.getString(2);
                String comuna = c.getString(3);
                String imagen = c.getString(4);

                Alumno alumno = new Alumno(rut, nombre,direccion, comuna, imagen);
                alumnos.add(alumno);
            }while(c.moveToNext());
            AlumnoAdapter adapter =new AlumnoAdapter(this, alumnos);
            GridView gridView = findViewById(R.id.gridView);
            gridView.setAdapter(adapter);
        }
        bd.close();
    }
}
