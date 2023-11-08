package com.example.proyectogridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AlumnoAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Alumno> alumnos;

    public AlumnoAdapter(Context context, ArrayList<Alumno> alumnos){
        this.context = context;
        this.alumnos = alumnos;
    }

    @Override
    public int getCount() {
        return alumnos.size();
    }

    @Override
    public Object getItem(int position) {
        return alumnos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }
        else{
            ImageView imageView = convertView.findViewById(R.id.itemImage);
            TextView title = convertView.findViewById(R.id.itemTitle);
            TextView description = convertView.findViewById(R.id.itemDesc);
            TextView sub = convertView.findViewById(R.id.itemSub);

            Alumno alumno = alumnos.get(position);

            title.setText(alumno.getNombre());
            description.setText(alumno.getDireccion());
            sub.setText(alumno.getNombre());
            String imagePath = alumno.getImagePath();
            try{
                InputStream inputStream = context.getContentResolver().openInputStream(Uri.parse(imagePath));
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }catch(IOException e) {
                Log.e("AlumnoAdapter", "Error al cargar la imagen", e);
            }
        }
        return convertView;
    }
}