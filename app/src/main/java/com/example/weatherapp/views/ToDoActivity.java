package com.example.weatherapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.controllers.CongViecAdapter;
import com.example.weatherapp.networking.Database;
import com.example.weatherapp.models.CongViec;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity {
    Database database;

    ArrayList<CongViec> arrayList;
    CongViecAdapter adapter;
    ListView listView;
    ImageView imageBack, imgAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        imageBack = findViewById(R.id.imageViewBackToDo);
        imgAdd   = findViewById(R.id.imageViewAdd);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ToDoActivity.this, MainActivity.class));
            }
        });

        listView = findViewById(R.id.listViewToDo);
        arrayList = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.todo_list, arrayList);
        listView.setAdapter(adapter);

        // tao database
        database = new Database(this, "ghichu.sqlite", null, 1);

        // tao bang database ghi chu
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");

        GetDataCongViec();

    }

    public void DeleteTodo(final int id){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Do you want to delete this?");

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QueryData("DELETE FROM CongViec WHERE Id = '"+id+"'");
                Toast.makeText(ToDoActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialog.show();
    }

    private void GetDataCongViec(){
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        arrayList.clear();
        while (dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            arrayList.add(new CongViec(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogUpdate(String ten, final int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);
        dialog.setCanceledOnTouchOutside(false);

        EditText edtUpdate = dialog.findViewById(R.id.editTextUpdateTodo);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdateToDo);
        Button btnCancel = dialog.findViewById(R.id.btnCancelUpdateTodo);

        edtUpdate.setText(ten);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMoi = edtUpdate.getText().toString();
                database.QueryData("UPDATE CongViec SET TenCV = '"+tenMoi+"' WHERE Id = '"+id+"'");
                Toast.makeText(ToDoActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void DialogAdd(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_todo);
        dialog.setCanceledOnTouchOutside(false);

        EditText edtTen     = dialog.findViewById(R.id.editTextAddTodo);
        Button btnAdd       = dialog.findViewById(R.id.btnAddToDo);
        Button btnCancel    = dialog.findViewById(R.id.btnCancelTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tencv = edtTen.getText().toString();
                if(tencv.equals("")){
                    Toast.makeText(ToDoActivity.this, "Please Add Something!", Toast.LENGTH_SHORT).show();
                }else{
                    database.QueryData("INSERT INTO CongViec VALUES(null, '"+tencv+"')");
                    Toast.makeText(ToDoActivity.this, "Added!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}