package com.project.gameregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    static RecyclerView recyclerView;
    private RecyclerviewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Memanggil fungsi untuk set Recycler View nya
        // Fungsi ini dibuat di Class ini ( Ada dibawah fungsi onCreate ini )
        setupRecyclerView();

        // Dengan try catch apabila ada kodingan yang error maka program akan tetap dijalankan
        // Disini menjalan kode yang dimana mengecek nilai intent
        // Karena Activity ini akan dibuka pertama kali maka kodingan ini bisa error dan program berhenti
        // Karena tidak ada extra apapun dari intent
        // Maka program didalam catch akan dijalankan
        try {
            Intent intent = getIntent();
            String status = intent.getExtras().getString("status");

            if (status.equals("add")) {
                Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            } else if (status.equals("edit")) {
                Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            if (userList.isEmpty()) {
                Toast.makeText(this, "Klik fab untuk menambah User", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Klik User untuk opsi lain", Toast.LENGTH_SHORT).show();
            }
        }

        // fab ini adalah tombol + di kanan bawah
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    // Fungsi yang didalamnya terdapat program untuk meng setup Recycler View
    // Fungsi ini dipakai di class lain
    // Saat ini digunakan untuk me refresh data recycler view saat menghapus data
    static void setupRecyclerView(Context context, List<User> userList, RecyclerView recyclerView) {
        DatabaseHelper db = new DatabaseHelper(context);
        userList = db.selectUserData();

        // Meng set adapter Recycler View nya
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // Fungsi ini sama namanya dengan yang sebelumnya
    // Begitupun fungsinya, tapi memiliki parameter dan aksesibilitas yang berbeda
    // Dalam OOP (Object Oriented Programming) 2 fungsi yang memiliki nama sama
    // Biasanya disebut Overloading
    // Fungsi yang ini dipakai untuk di class ini
    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(this);
        userList = db.selectUserData();

        adapter = new RecyclerviewAdapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Selamat datang ^_^", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Halo ^_^", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Jangan lupa kembali lagi ^_^", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Sampai berjumpa lagi nanti ^_^", Toast.LENGTH_LONG).show();
    }
}