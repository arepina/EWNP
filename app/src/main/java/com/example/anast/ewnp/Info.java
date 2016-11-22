package com.example.anast.ewnp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    TextView info, infotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        info = (TextView) findViewById(R.id.info1);
        infotext = (TextView) findViewById(R.id.infotest);
        info.setText("Справка");
        infotext.setText("Данное приложение создано в результате работы над проектом, целью которого являлась помощь при подготовке к экзамену по английскому языку в 7 классе.\n" +
                "Приложение содержит в себе два режима подготовки: запоминание и проверка.\n" +
                "Присутствует возможность добавления в словари своих слов, а также их удаление.\n" +
                "Присутствует возможность смены языка.\n" +
                "При возникновении у Вас вопросов или обнаружении неполадок Вы сможете написать нам с помощью формы обратной связи, нажав на письмо в главном меню.\n\n" +
                "Сделано Анастасией и Елизаветой Репиными");
        ImageButton home = (ImageButton) findViewById(R.id.homeinfo);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishActivity(2);
                Intent intent = new Intent(Info.this, MainActivity.class);
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
            finishActivity(2);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            // set the animation to move once the button is clicked
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return true;
        }
        return false;
    }
}
