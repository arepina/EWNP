package com.example.anast.ewnp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class MemActivity extends AppCompatActivity {

    Map<Integer, String[]> map;
    int now = 0;
    TextView rus;
    TextView eng;
    String code;
    TextView topic;
    SoundPool mediaPlayer;
    AssetManager assets;
    int sound = 0;
    int streamId = 0;
    WordOperationsEnv studentDBoperationenv;
    WordOperationsIM studentDBoperationim;
    WordOperationsSport studentDBoperationsp;
    TextView error;
    List values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem);
        code = getIntent().getExtras().getString("theme");
        ImageButton next = (ImageButton) findViewById(R.id.nextbut);
        ImageButton prev = (ImageButton) findViewById(R.id.prevbut);
        ImageButton sound = (ImageButton) findViewById(R.id.soundsbut);
        topic = (TextView) findViewById(R.id.myidtextView);
        error = (TextView) findViewById(R.id.error);
        error.setVisibility(View.INVISIBLE);
        rus = (TextView) findViewById(R.id.rustext);
        eng = (TextView) findViewById(R.id.engtext);
        ImageButton home = (ImageButton) findViewById(R.id.homemem);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mediaPlayer != null) {
                        mediaPlayer.stop(streamId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
                finishActivity(5);
                Intent intent = new Intent(MemActivity.this, ThemeChoosing.class);
                startActivity(intent);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        if (code.equals("5")) {
            studentDBoperationim = new WordOperationsIM(this);
            studentDBoperationim.open();
            values = studentDBoperationim.getAllStudents();
            map = Dictionary.techno();
            if (MainActivity.ruschoosen) {
                topic.setText("Интернет и масс-медиа");
            } else {
                topic.setText("Internet and mass - media");
            }
            rus.setText(map.get(now)[1]);
            eng.setText(map.get(now)[0]);
            int h = map.size();
            for(int i = 0; i < values.size(); i++)
            {
                String[] res = Check.parse(values.get(i).toString());
                map.put(h, res);
                h++;
            }
        }
        if (code.equals("6")) {
            studentDBoperationsp = new WordOperationsSport(this);
            studentDBoperationsp.open();
            values = studentDBoperationsp.getAllStudents();
            map = Dictionary.sport();
            if (MainActivity.ruschoosen) {
                topic.setText("Спорт");
            } else {
                topic.setText("Sport");
            }
            rus.setText(map.get(now)[1]);
            eng.setText(map.get(now)[0]);
            int h = map.size();
            for(int i = 0; i < values.size(); i++)
            {
                String[] res = Check.parse(values.get(i).toString());
                map.put(h, res);
                h++;
            }
        }
        if (code.equals("7")) {
            studentDBoperationenv = new WordOperationsEnv(this);
            studentDBoperationenv.open();
            values = studentDBoperationenv.getAllStudents();
            map = Dictionary.eco();
            if (MainActivity.ruschoosen) {
                topic.setText("Окружающая среда");
            } else {
                topic.setText("Environment");
            }
            rus.setText(map.get(now)[1]);
            eng.setText(map.get(now)[0]);
            int h = map.size();
            for(int i = 0; i < values.size(); i++)
            {
                String[] res = Check.parse(values.get(i).toString());
                map.put(h, res);
                h++;
            }
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (now + 1 != map.size()) {
                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.stop(streamId);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    error.setVisibility(View.INVISIBLE);
                    now++;
                    rus.setText(map.get(now)[1]);
                    eng.setText(map.get(now)[0]);
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (now != 0) {
                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.stop(streamId);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    error.setVisibility(View.INVISIBLE);
                    now--;
                    rus.setText(map.get(now)[1]);
                    eng.setText(map.get(now)[0]);
                }
            }
        });
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer != null)
                    mediaPlayer.release();
                mediaPlayer = null;
                playMusic(now, (String) topic.getText());
            }
        });
    }

    void playMusic(int i, String s) {
        assets = getAssets();
        mediaPlayer = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        switch (s) {
            case "Окружающая среда": {
                Environment(i);
                break;
            }
            case "Environment": {
                Environment(i);
                break;
            }
            case "Спорт": {
                Sport(i);
                break;
            }
            case "Sport": {
                Sport(i);
                break;
            }
            case "Интернет и масс-медиа": {
                In(i);
                break;
            }
            case "Internet and mass - media": {
                In(i);
                break;
            }
        }
    }

    protected void playSound(int s) {
        if (s > 0) {
            streamId = mediaPlayer.play(s, 1, 1, 1, 0, 1);
        }
        else
        {
            error.setVisibility(View.VISIBLE);
        }
    }

    void Environment(int i) {
        switch (i) {
            case 0: {
                sound = mediaPlayer.load(this, R.raw.world, 1);
                break;
            }
            case 1: {
                sound = mediaPlayer.load(this, R.raw.revolution, 1);
                break;
            }
            case 2: {
                sound = mediaPlayer.load(this, R.raw.rubbish, 1);
                break;
            }
            case 3: {
                sound = mediaPlayer.load(this, R.raw.pollution, 1);
                break;
            }
            case 4: {
                sound = mediaPlayer.load(this, R.raw.rainforest, 1);
                break;
            }
            case 5: {
                sound = mediaPlayer.load(this, R.raw.clean, 1);
                break;
            }
            case 6: {
                sound = mediaPlayer.load(this, R.raw.trees, 1);
                break;
            }
            case 7: {
                sound = mediaPlayer.load(this, R.raw.ecology, 1);
                break;
            }
            case 8: {
                sound = mediaPlayer.load(this, R.raw.animal_world, 1);
                break;
            }
            case 9: {
                sound = mediaPlayer.load(this, R.raw.flora, 1);
                break;
            }
            case 10: {
                sound = mediaPlayer.load(this, R.raw.oxygen, 1);
                break;
            }
            case 11: {
                sound = mediaPlayer.load(this, R.raw.dump, 1);
                break;
            }
            case 12: {
                sound = mediaPlayer.load(this, R.raw.earthquake, 1);
                break;
            }
            case 13: {
                sound = mediaPlayer.load(this, R.raw.flooding, 1);
                break;
            }
            case 14: {
                sound = mediaPlayer.load(this, R.raw.hurricane, 1);
                break;
            }
            case 15: {
                sound = mediaPlayer.load(this, R.raw.pond, 1);
                break;
            }
            case 16: {
                sound = mediaPlayer.load(this, R.raw.wastes, 1);
                break;
            }
            case 17: {
                sound = mediaPlayer.load(this, R.raw.air, 1);
                break;
            }
            case 18: {
                sound = mediaPlayer.load(this, R.raw.earth, 1);
                break;
            }
            case 19: {
                sound = mediaPlayer.load(this, R.raw.to_clean_up, 1);
                break;
            }
            default: {
                sound = -1;
            }
        }
        playSound(sound);
    }

    void Sport(int i) {
        switch (i) {
            case 0: {
                sound = mediaPlayer.load(this, R.raw.goal, 1);
                break;
            }
            case 1: {
                sound = mediaPlayer.load(this, R.raw.player, 1);
                break;
            }
            case 2: {
                sound = mediaPlayer.load(this, R.raw.championship, 1);
                break;
            }
            case 3: {
                sound = mediaPlayer.load(this, R.raw.score, 1);
                break;
            }
            case 4: {
                sound = mediaPlayer.load(this, R.raw.in_a_draw, 1);
                break;
            }
            case 5: {
                sound = mediaPlayer.load(this, R.raw.to_score, 1);
                break;
            }
            case 6: {
                sound = mediaPlayer.load(this, R.raw.reserve, 1);
                break;
            }
            case 7: {
                sound = mediaPlayer.load(this, R.raw.stadium, 1);
                break;
            }
            case 8: {
                sound = mediaPlayer.load(this, R.raw.team, 1);
                break;
            }
            case 9: {
                sound = mediaPlayer.load(this, R.raw.racket, 1);
                break;
            }
            case 10: {
                sound = mediaPlayer.load(this, R.raw.to_win, 1);
                break;
            }
            case 11: {
                sound = mediaPlayer.load(this, R.raw.medal, 1);
                break;
            }
            case 12: {
                sound = mediaPlayer.load(this, R.raw.referee, 1);
                break;
            }
            case 13: {
                sound = mediaPlayer.load(this, R.raw.net, 1);
                break;
            }
            case 14: {
                sound = mediaPlayer.load(this, R.raw.defender, 1);
                break;
            }
            case 15: {
                sound = mediaPlayer.load(this, R.raw.defeat, 1);
                break;
            }
            case 16: {
                sound = mediaPlayer.load(this, R.raw.spectator, 1);
                break;
            }
            case 17: {
                sound = mediaPlayer.load(this, R.raw.field, 1);
                break;
            }
            case 18: {
                sound = mediaPlayer.load(this, R.raw.whistle, 1);
                break;
            }
            case 19: {
                sound = mediaPlayer.load(this, R.raw.to_train, 1);
                break;
            }
            case 20: {
                sound = mediaPlayer.load(this, R.raw.pass, 1);
                break;
            }
            default: {
                sound = -1;
            }
        }
        playSound(sound);
    }

    void In(int i) {
        switch (i) {
            case 0: {
                sound = mediaPlayer.load(this, R.raw.letter, 1);
                break;
            }
            case 1: {
                sound = mediaPlayer.load(this, R.raw.to_type, 1);
                break;
            }
            case 2: {
                sound = mediaPlayer.load(this, R.raw.to_send, 1);
                break;
            }
            case 3: {
                sound = mediaPlayer.load(this, R.raw.to_get, 1);
                break;
            }
            case 4: {
                sound = mediaPlayer.load(this, R.raw.message, 1);
                break;
            }
            case 5: {
                sound = mediaPlayer.load(this, R.raw.website, 1);
                break;
            }
            case 6: {
                sound = mediaPlayer.load(this, R.raw.to_search, 1);
                break;
            }
            case 7: {
                sound = mediaPlayer.load(this, R.raw.to_find, 1);
                break;
            }
            case 8: {
                sound = mediaPlayer.load(this, R.raw.source, 1);
                break;
            }
            case 9: {
                sound = mediaPlayer.load(this, R.raw.link, 1);
                break;
            }
            case 10: {
                sound = mediaPlayer.load(this, R.raw.user, 1);
                break;
            }
            case 11: {
                sound = mediaPlayer.load(this, R.raw.cognitive, 1);
                break;
            }
            case 12: {
                sound = mediaPlayer.load(this, R.raw.to_download, 1);
                break;
            }
            case 13: {
                sound = mediaPlayer.load(this, R.raw.editor, 1);
                break;
            }
            case 14: {
                sound = mediaPlayer.load(this, R.raw.advertising, 1);
                break;
            }
            case 15: {
                sound = mediaPlayer.load(this, R.raw.brief_article, 1);
                break;
            }
            case 16: {
                sound = mediaPlayer.load(this, R.raw.title, 1);
                break;
            }
            case 17: {
                sound = mediaPlayer.load(this, R.raw.pictures, 1);
                break;
            }
            case 18: {
                sound = mediaPlayer.load(this, R.raw.login, 1);
                break;
            }
            case 19: {
                sound = mediaPlayer.load(this, R.raw.article, 1);
                break;
            }
            case 20: {
                sound = mediaPlayer.load(this, R.raw.information, 1);
                break;
            }
            default: {
                sound = -1;
            }
        }
        playSound(sound);
    }

    //обработка нажатия на аппаратную кнопку назад
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.stop(streamId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finish();
            finishActivity(5);
            Intent intent = new Intent(this, ThemeChoosing.class);
            startActivity(intent);
            // set the animation to move once the button is clicked
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return true;
        }
        return false;
    }
}