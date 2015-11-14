package com.futuroinginiero.dsmr;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class Main2Activity extends AppCompatActivity {



    String url_imagen;

    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String name=getIntent().getStringExtra("NOMBRE");

        TextView user_name=(TextView)findViewById(R.id.text_name_user);
        user_name.setText(name);

        imagen=(ImageView)findViewById(R.id.imageView_user);

        url_imagen=getIntent().getStringExtra("FOTO");

        DescargaImagen m=new DescargaImagen();
        m.execute();



    }






    class DescargaImagen extends AsyncTask<Void,Void,Void>{

        Bitmap descarga =null;
        @Override
        protected Void doInBackground(Void... params) {

            try {//descargamos la imagen de perfil del usuario
                descarga = Picasso.with(Main2Activity.this).load(url_imagen).get();

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {

           if(descarga!=null){
               imagen.setImageBitmap(descarga);
           }
            super.onPostExecute(aVoid);
        }
    }



}
