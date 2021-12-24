package com.project.gameregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    // Pendeklarasian Variabel
    private TextView txtNamaLengkap;
    private TextView txtNickname;
    private TextView txtEmail;
    private TextView txtDomisili;
    private TextView txtTurnamen;
    private TextView txtSumber;
    private TextView txtRating;
    private String namaLengkap;
    private String nickname;
    private String email;
    private String domisili;
    private String turnament;
    private String sumber;
    private String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        txtNamaLengkap = findViewById(R.id.isi_namalengkap);
        txtNickname = findViewById(R.id.isi_nickname);
        txtEmail = findViewById(R.id.isi_email);
        txtDomisili = findViewById(R.id.isi_domisili);
        txtTurnamen = findViewById(R.id.isi_turnamen);
        txtSumber = findViewById(R.id.isi_sumber);
        txtRating = findViewById(R.id.isi_rating);

        Intent intent = getIntent();
        namaLengkap = intent.getExtras().getString("namaLengkap");
        nickname = intent.getExtras().getString("nickname");
        email = intent.getExtras().getString("email");
        domisili = intent.getExtras().getString("domisili");
        turnament = intent.getExtras().getString("turnament");
        sumber = intent.getExtras().getString("sumber");
        rating = intent.getExtras().getString("rating");

        txtNamaLengkap.setText(namaLengkap);
        txtNickname.setText(nickname);
        txtEmail.setText(email);
        txtDomisili.setText(domisili);
        txtTurnamen.setText(turnament);
        txtSumber.setText(sumber);
        txtRating.setText(rating);
    }
}