package com.example.anast.ewnp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CheckActivity extends AppCompatActivity {

    Map<Integer, String[]> map;
    List<Integer> shuffled_nums;
    int now = 0;
    TextView rus;
    EditText eng;
    String code;
    int cur_size = 0;
    WordOperationsEnv studentDBoperationenv;
    WordOperationsIM studentDBoperationim;
    WordOperationsSport studentDBoperationsp;
    List values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        code = getIntent().getExtras().getString("theme");
        ImageButton next = (ImageButton)findViewById(R.id.nextbut1);
        ImageButton help = (ImageButton)findViewById(R.id.helpbut);
        ImageButton home = (ImageButton)findViewById(R.id.homecheck);
        TextView topic = (TextView)findViewById(R.id.myidtextView1);
        rus = (TextView)findViewById(R.id.rustext1);
        eng = (EditText)findViewById(R.id.checktext);
        if(code.equals("5"))
        {
            studentDBoperationim = new WordOperationsIM(this);
            studentDBoperationim.open();
            values = studentDBoperationim.getAllStudents();
            map = Dictionary.techno();
            if(MainActivity.ruschoosen)
            {
                topic.setText("Интернет и масс-медиа");
            }
            else {
                topic.setText("Internet and mass - media");
            }
            int h = map.size();
            for(int i = 0; i < values.size(); i++)
            {
                String[] res = Check.parse(values.get(i).toString());
                map.put(h, res);
                h++;
            }
            shuffled_nums = new ArrayList<>(map.size());
            cur_size = map.size();
        }
        if(code.equals("6"))
        {
            studentDBoperationsp = new WordOperationsSport(this);
            studentDBoperationsp.open();
            values = studentDBoperationsp.getAllStudents();
            map = Dictionary.sport();
            if(MainActivity.ruschoosen)
            {
                topic.setText("Спорт");
            }
            else {
                topic.setText("Sport");
            }
            int h = map.size();
            for(int i = 0; i < values.size(); i++)
            {
                String[] res = Check.parse(values.get(i).toString());
                map.put(h, res);
                h++;
            }
            shuffled_nums = new ArrayList<>(map.size());
            cur_size = map.size();
        }
        if(code.equals("7"))
        {
            studentDBoperationenv = new WordOperationsEnv(this);
            studentDBoperationenv.open();
            values = studentDBoperationenv.getAllStudents();
            map = Dictionary.eco();
            if(MainActivity.ruschoosen)
            {
                topic.setText("Окружающая среда");
            }
            else {
                topic.setText("Environment");
            }
            int h = map.size();
            for(int i = 0; i < values.size(); i++)
            {
                String[] res = Check.parse(values.get(i).toString());
                map.put(h, res);
                h++;
            }
            shuffled_nums = new ArrayList<>(map.size());
            cur_size = map.size();
        }
        for(int i = 0; i < cur_size; i++)
        {
            shuffled_nums.add(i);
        }
        Collections.shuffle(shuffled_nums);
        now = shuffled_nums.get(0);
        rus.setText(map.get(now)[1]);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = eng.getText().toString();
                s = s.trim().replaceAll("\\s+", " ");
                eng.setText(s);
                if (eng.getText().length() != 0 & !map.get(now)[0].equals(eng.getText().toString())) {
                    if (MainActivity.ruschoosen) {
                        Toast.makeText(getApplicationContext(), "Ответ неверный, попробуй еще раз!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Your answer is incorrect, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!map.get(now)[0].equals(eng.getText().toString())) {
                    if (MainActivity.ruschoosen) {
                        Toast.makeText(getApplicationContext(), "Прежде чем перейти к следующему заданию, необходимо выполнить данное!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "You need to do this task before switching to the next one!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    now++;
                    try {
                        rus.setText(map.get(now)[1]);
                    }
                    catch (Exception e)
                    {
                        now = 0;
                        Collections.shuffle(shuffled_nums);
                        rus.setText(map.get(now)[1]);
                    }
                    eng.setText("");
                }
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eng.setText(map.get(now)[0]);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishActivity(6);
                Intent intent = new Intent(CheckActivity.this, ThemeChoosingCheckActivity.class);
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
            finishActivity(6);
            Intent intent = new Intent(this, ThemeChoosingCheckActivity.class);
            startActivity(intent);
            // set the animation to move once the button is clicked
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return true;
        }
        return false;
    }
}
