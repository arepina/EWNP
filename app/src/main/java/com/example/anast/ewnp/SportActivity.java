package com.example.anast.ewnp;


import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SportActivity extends AppCompatActivity implements SwipeListView.SwipeListViewCallback {

    private WordOperationsSport studentDBoperation;
    private MyAdapter m_Adapter;//адаптер списка всех объектов на экране, чтобы была возможность их удалить
    List values;
    ListView list;

    //класс, реализующий удаление элемента из списка по свайпу
    public class MyAdapter extends BaseAdapter {

        private final int invalid = -1;
        protected int delete_pos = -1;

        protected List<String> m_List;

        public MyAdapter() {
            m_List = new ArrayList<>();
        }

        public void n()
        {
            notifyDataSetChanged();
        }

        public void addItem(String item) {
            m_List.add(item);
            notifyDataSetChanged();
        }

        public void onSwipeItem(boolean isRight, int position) {
            if (!isRight) {
                delete_pos = position;
            } else if (delete_pos == position) {
                delete_pos = invalid;
            }
            notifyDataSetChanged();
        }

        public void deleteItem(int pos) {
            m_List.remove(pos);
            WordOperationsSport.database.delete(DataBaseWrapperSport.WORDS, DataBaseWrapperSport.WORD_ID + " = " + pos, null);
            delete_pos = invalid;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return m_List.size();
        }

        @Override
        public String getItem(int position) {
            return m_List.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(SportActivity.this).inflate(R.layout.item, null);
            }
            TextView text = ViewHolderPattern.get(convertView, R.id.text);
            Button delete = ViewHolderPattern.get(convertView, R.id.delete);
            if (delete_pos == position) {
                delete.setVisibility(View.VISIBLE);
            } else
                delete.setVisibility(View.GONE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });
            text.setText(getItem(position));
            return convertView;
        }
    }

    //класс, свзанный с удалением элемента из списка из определенного holdera
    public static class ViewHolderPattern {
        @SuppressWarnings("unchecked")
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<>();
                view.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ImageButton home = (ImageButton)findViewById(R.id.homesp);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishActivity(26);
                Intent intent = new Intent(SportActivity.this, NewWordToDictionary.class);
                startActivity(intent);
                // set the animation to move once the button is clicked
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

        studentDBoperation = new WordOperationsSport(this);
        studentDBoperation.open();
        values = studentDBoperation.getAllStudents();
        list = (ListView) findViewById(R.id.objectslistsp);
        SwipeListView l = new SwipeListView(this, this);
        l.exec();
        m_Adapter = new MyAdapter();
        try {
            if (values.size() != 0) {
                for (int i = 0; i < values.size(); i++) {
                    m_Adapter.addItem(values.get(i).toString());
                }
                list.setAdapter(m_Adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //обработка нажатия на аппаратную кнопку назад
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            finishActivity(26);
            Intent intent = new Intent(SportActivity.this, NewWordToDictionary.class);
            startActivity(intent);
            // set the animation to move once the button is clicked
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return true;
        }
        return false;
    }

    @Override
    public ListView getListView() {
        return list;
    }

    //свайп по элементу списка объектов
    @Override
    public void onSwipeItem(boolean isRight, int position) {
        m_Adapter.onSwipeItem(isRight, position);
    }

    //нажатие на элемент списка объектов
    @Override
    public void onItemClickListener(ListAdapter adapter, int position) {
    }

    public void addUser(View view) {
        EditText rus = (EditText) findViewById(R.id.editruswordsp);
        EditText eng = (EditText) findViewById(R.id.editengwordsp);
        if(!Check.rus(rus.getText().toString().toLowerCase()) || !Check.eng(eng.getText().toString().toLowerCase()))
        {
            Toast.makeText(getApplicationContext(), "Введи корректные данные", Toast.LENGTH_SHORT).show();
        }
        else {
            WordSport stud = studentDBoperation.addStudent(rus.getText().toString(), eng.getText().toString(), m_Adapter.getCount());
            values = studentDBoperation.getAllStudents();
            m_Adapter = new MyAdapter();
            try {
                if (values.size() != 0) {
                    for (int i = 0; i < values.size(); i++) {
                        m_Adapter.addItem(values.get(i).toString());
                    }
                    list.setAdapter(m_Adapter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onResume() {
        studentDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        studentDBoperation.close();
        super.onPause();
    }

}