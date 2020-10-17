package com.example.pocketnews;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

@Override
    protected void onCreate(Bundle savedInstanceState)
    {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    }

    public void buttonUserClick (View v)
    {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    public void buttonExitClick (View v)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void buttonSyleClick (View v)
    {

    }

    public void buttonBusinessClick (View v)
    {

    }

    public void buttonSportsClick (View v)
    {

    }

    public void buttonWorldClick (View v)
    {

    }

    public void buttonHealthClick (View v)
    {

    }

    public void buttonTechClick (View v)
    {

    }

    public void buttonEntertainClick (View v)
    {

    }

    public void buttonPoliticsClick (View v)
    {

    }
}
