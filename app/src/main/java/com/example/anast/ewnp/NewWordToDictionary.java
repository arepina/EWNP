package com.example.anast.ewnp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewWordToDictionary extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word_to_dictionary);
        Button techno = (Button)findViewById(R.id.technobutdict);
        Button sport = (Button)findViewById(R.id.sportbutdict);
        Button eco = (Button)findViewById(R.id.ecobutdict);
        text = (TextView)findViewById(R.id.textTopicdict);
        techno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewWordToDictionary.this, IMActivity.class);
                startActivityForResult(intent, 25);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewWordToDictionary.this, SportActivity.class);
                startActivityForResult(intent, 26);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewWordToDictionary.this, EnvActivity.class);
                startActivityForResult(intent, 27);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        if(MainActivity.ruschoosen)
        {
            techno.setText("Интернет и масс-медиа");
            sport.setText("Спорт");
            eco.setText("Окружающая среда");
            text.setText("Выбери словарь");
        }
        else
        {
            techno.setText("Internet and mass - media");
            sport.setText("Sport");
            eco.setText("Environment");
            text.setText("Choose the dictionary");
        }
        ImageButton home = (ImageButton) findViewById(R.id.homedict);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishActivity(9);
                Intent intent = new Intent(NewWordToDictionary.this, ModeChoosing.class);
                startActivity(intent);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
    }

    //обработка нажатия на аппаратную кнопку назад
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            finishActivity(9);
            Intent intent = new Intent(NewWordToDictionary.this, ModeChoosing.class);
            startActivity(intent);
            // set the animation to move once the button is clicked
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return true;
        }
        return false;
    }
}
