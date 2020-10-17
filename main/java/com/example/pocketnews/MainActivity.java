package com.example.pocketnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button homeLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); // hide the title bar

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        homeLogin = findViewById(R.id.HomeLogin);

        homeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



//        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    Document doc = Jsoup.connect("https://www.cnn.com").get();
////                    String link = doc.location();
////                    Log.d("testing", doc.title());
////                    Log.d("testing", link);
////                }
////                catch (IOException e){
////                    e.printStackTrace();
////                }
////            }
////        }).start();


        //get all the information and set it up

        Database data = new Database();
        data.update();


        ArrayList<String> links = data.getLinks();
        for (String l : links){
            Log.d("testing", l);
        }
    }
}
