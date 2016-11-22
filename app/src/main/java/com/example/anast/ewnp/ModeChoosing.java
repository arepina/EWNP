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

public class ModeChoosing extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_choosing);
        Button mem = (Button)findViewById(R.id.membut);
        Button check = (Button)findViewById(R.id.checkbut);
        Button addw = (Button)findViewById(R.id.correctdictbut);
        text = (TextView)findViewById(R.id.textTask123);
        mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModeChoosing.this, ThemeChoosing.class);
                startActivityForResult(intent, 3);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModeChoosing.this, ThemeChoosingCheckActivity.class);
                startActivityForResult(intent, 4);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        addw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModeChoosing.this, NewWordToDictionary.class);
                startActivityForResult(intent, 9);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        ImageButton home = (ImageButton) findViewById(R.id.homemodecoo);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishActivity(1);
                Intent intent = new Intent(ModeChoosing.this, MainActivity.class);
                startActivity(intent);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        if(MainActivity.ruschoosen)
        {
            mem.setText("Запоминание");
            check.setText("Проверка");
            addw.setText("Добавление слов");
            text.setText("Выбери режим");
        }
        else
        {
            mem.setText("Memorize");
            check.setText("Check");
            addw.setText("Add words");
            text.setText("Choose the mode");
        }
    }

    //обработка нажатия на аппаратную кнопку назад
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            finishActivity(1);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            // set the animation to move once the button is clicked
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return true;
        }
        return false;
    }
}
