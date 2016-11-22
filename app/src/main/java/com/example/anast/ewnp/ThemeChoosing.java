package com.example.anast.ewnp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ThemeChoosing extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_choosing);
        Button techno = (Button)findViewById(R.id.technobut);
        Button sport = (Button)findViewById(R.id.sportbut);
        Button eco = (Button)findViewById(R.id.ecobut);
        text = (TextView)findViewById(R.id.textTopic);
        techno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeChoosing.this, MemActivity.class);
                intent.putExtra("theme", "5");
                startActivityForResult(intent, 5);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeChoosing.this, MemActivity.class);
                intent.putExtra("theme", "6");
                startActivityForResult(intent, 5);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeChoosing.this, MemActivity.class);
                intent.putExtra("theme", "7");
                startActivityForResult(intent, 5);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        if(MainActivity.ruschoosen)
        {
            techno.setText("Интернет и масс-медиа");
            sport.setText("Спорт");
            eco.setText("Окружающая среда");
            text.setText("Выбери тему");
        }
        else
        {
            techno.setText("Internet and mass - media");
            sport.setText("Sport");
            eco.setText("Environment");
            text.setText("Choose the topic");
        }
        ImageButton home = (ImageButton) findViewById(R.id.homethemecoo);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishActivity(3);
                Intent intent = new Intent(ThemeChoosing.this, ModeChoosing.class);
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
            finishActivity(3);
            Intent intent = new Intent(this, ModeChoosing.class);
            startActivity(intent);
            // set the animation to move once the button is clicked
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return true;
        }
        return false;
    }
}
