package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToDoActivity extends AppCompatActivity {
    List<String> toDoList;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText edtToDo;
    ImageView imagBack;
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
//    private Integer i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        imagBack = findViewById(R.id.imageViewBackToDo);
        toDoList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.todo_list, toDoList);
        listView = findViewById(R.id.listViewToDo);
        edtToDo  = findViewById(R.id.editTextToDo);

//        sharedPreferences = getSharedPreferences("todolist", MODE_PRIVATE);
//        for (int j=0; j<i; j++){
//            toDoList.set(j, sharedPreferences.getString("todo" + j, "cant find"));
//        }

        imagBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                toDoList.remove(toDoList.get(i));
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void addItem(View view){
        toDoList.add(edtToDo.getText().toString());

//        editor = sharedPreferences.edit();
//        editor.putString("todo"+i, edtToDo.getText().toString().trim());
//        i++;
//        editor.commit();

        adapter.notifyDataSetChanged();
        edtToDo.setText("");
    }
}