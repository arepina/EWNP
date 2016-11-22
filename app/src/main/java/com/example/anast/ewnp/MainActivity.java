package com.example.anast.ewnp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public static boolean ruschoosen = false;
    Button task, info;
    ImageButton exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = (Button) findViewById(R.id.taskbut);
        info = (Button) findViewById(R.id.infobut);
        exit = (ImageButton) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ruschoosen) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Выход");
                    alertDialogBuilder.setMessage("Ты уверен, что хочешь выйти из приложения?");
                    alertDialogBuilder.setPositiveButton("Да, я уверен", new DialogInterface.OnClickListener() {// set positive button: Yes message
                        public void onClick(DialogInterface dialog, int id) {
                            finishAffinity();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("Нет, верните меня в главное меню", new DialogInterface.OnClickListener() {// set negative button: No message
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Exit");
                    alertDialogBuilder.setMessage("Are you sure you want to exit the application?");
                    alertDialogBuilder.setPositiveButton("Yes, I'm sure", new DialogInterface.OnClickListener() {// set positive button: Yes message
                        public void onClick(DialogInterface dialog, int id) {
                            finishAffinity();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No, let me return to the main menu", new DialogInterface.OnClickListener() {// set negative button: No message
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ModeChoosing.class);
                startActivityForResult(intent, 1);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                startActivityForResult(intent, 2);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_forw, R.anim.slide_back);
            }
        });
        ImageButton feedback = (ImageButton) findViewById(R.id.contact);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "EWNP");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setData(Uri.parse("mailto:prostorepa@yandex.ru"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        ImageButton rus = (ImageButton) findViewById(R.id.RuslangChange);
        ImageButton eng = (ImageButton) findViewById(R.id.EnglangChange);
        rus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ruschoosen = true;
                task.setText("Задание");
                info.setText("Справка");
            }
        });
        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ruschoosen = false;
                task.setText("Tasks");
                info.setText("Info");
            }
        });
        if (ruschoosen) {
            task.setText("Задание");
            info.setText("Справка");
        } else {
            task.setText("Tasks");
            info.setText("Info");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ruschoosen) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Выход");
                alertDialogBuilder.setMessage("Ты уверен, что хочешь выйти из приложения?");
                alertDialogBuilder.setPositiveButton("Да, я уверен", new DialogInterface.OnClickListener() {// set positive button: Yes message
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                });
                alertDialogBuilder.setNegativeButton("Нет, верните меня в главное меню", new DialogInterface.OnClickListener() {// set negative button: No message
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Exit");
                alertDialogBuilder.setMessage("Are you sure you want to exit the application?");
                alertDialogBuilder.setPositiveButton("Yes, I'm sure", new DialogInterface.OnClickListener() {// set positive button: Yes message
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                });
                alertDialogBuilder.setNegativeButton("No, let me return to the main menu", new DialogInterface.OnClickListener() {// set negative button: No message
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        }
        return false;
    }

}
