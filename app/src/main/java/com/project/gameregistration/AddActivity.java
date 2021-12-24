package com.project.gameregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    // Pendeklarasian Variabel
    private EditText txtNamaLengkap;
    private String namaLengkap;
    private EditText txtNickname;
    private String nickname;
    private EditText txtEmail;
    private String email;
    private EditText txtDomisili;
    private String domisili;
    private RadioGroup rgTurnamen;
    private RadioButton rbMlbb;
    private RadioButton rbLolwr;
    private RadioButton rbFf;
    private RadioButton rbPubgm;
    private RadioButton rbCodm;
    private RadioButton rbValorant;
    private String turnamen;
    private CheckBox cbInstagram;
    private CheckBox cbDiscord;
    private CheckBox cbTwitch;
    private CheckBox cbYoutube;
    private CheckBox cbNimoTV;
    private CheckBox cbLainnya;
    private String sumber;
    private SeekBar seekBar;
    private TextView txtRating;
    private String nilaiRating;
    private Button btnDaftar;

    private DatabaseHelper db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Inisialisasi Variabel
        txtNamaLengkap = findViewById(R.id.input_namalengkap);
        txtNickname = findViewById(R.id.input_nickname);
        txtEmail = findViewById(R.id.input_email);
        txtDomisili = findViewById(R.id.input_domisili);
        rgTurnamen = findViewById(R.id.rgturnamen);
        rbMlbb = findViewById(R.id.mlbb);
        rbLolwr = findViewById(R.id.lolwr);
        rbFf = findViewById(R.id.freefire);
        rbPubgm = findViewById(R.id.pubgm);
        rbCodm = findViewById(R.id.codm);
        rbValorant = findViewById(R.id.valo);
        cbInstagram = findViewById(R.id.instagram);
        cbDiscord = findViewById(R.id.discord);
        cbTwitch = findViewById(R.id.twitch);
        cbYoutube = findViewById(R.id.youtube);
        cbNimoTV = findViewById(R.id.nimotv);
        cbLainnya = findViewById(R.id.lainnya);
        seekBar = findViewById(R.id.seekbar);
        txtRating = findViewById(R.id.rating);
        btnDaftar = findViewById(R.id.daftar);

        db = new DatabaseHelper(this);

        // Fungsi seekbar untuk mengambil datanya
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilaiRating = String.valueOf(i);
                txtRating.setText("Beri nilai tim kami : " + nilaiRating);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Fungsi button saat di klik
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaLengkap = txtNamaLengkap.getText().toString().trim();
                nickname = txtNickname.getText().toString().trim();
                email = txtEmail.getText().toString().trim();
                domisili = txtDomisili.getText().toString().trim();

                // Mengisi nilai dari hasil return sebuah method
                turnamen = getTurnamenSelected();
                sumber = getSumberSelected();

                // Inisialisasi Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                builder.setIcon(R.drawable.warning); // Mengeset Icon
                builder.setTitle("Daftarkan"); // Mengeset Title

                // Mengeset Message
                builder.setMessage(
                        "Apakah anda sudah yakin dengan data anda ?\n\n" +
                                "Nama Lengkap : \n" + namaLengkap + "\n\n" +
                                "Nickname : \n" + nickname + "\n\n" +
                                "Email : \n" + email + "\n\n" +
                                "Domisili : \n" + domisili + "\n\n" +
                                "Turnament : \n" + turnamen + "\n\n" +
                                "Sumber : \n" + sumber + "\n\n" +
                                "Rating : \n" + nilaiRating + ""
                );

                //method button positive desicion
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Memunculkan Toast
                        Toast.makeText(getApplicationContext(), "Data anda berhasil terdaftarkan !", Toast.LENGTH_SHORT).show();

                        // Inisialisasi Intent untuk berpindah activity
                        Intent layoutMenu = new Intent(AddActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        layoutMenu.putExtra("status", "add");

                        // Membuat user dan memasukannya pada database SQLite
                        user = new User();
                        user.setNamaLengkap(namaLengkap);
                        user.setNickname(nickname);
                        user.setEmail(email);
                        user.setDomisili(domisili);
                        user.setTurnament(turnamen);
                        user.setSumber(sumber);
                        user.setRating(nilaiRating);

                        // Memanggil/memakai fungsi insert untuk memasukkan data
                        db.insert(user);

                        // Memulai Activity tujuan
                        startActivity(layoutMenu);
                        finish();
                    }
                });

                //method button negative desicion
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                // Memunculkan alert
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    // Method atau fungsi untuk mengambil data dari Checkbox yang tercheck
    // Method atau fungsi ini akan mengembalikan data berupa String
    private String getSumberSelected() {
        String sumber = "";

        // Jika Checkbox Instagram tercheck
        if (cbInstagram.isChecked()) {
            // Maka variabel sumber akan ditambah sama dengan datanya dengan - Instagram
            // \n disini adalah enter
            // += berarti atau sama dengan penulisan :
            // sumber = sumber + "- Instagram\n"
            sumber += "- Instagram\n";
        }
        if (cbDiscord.isChecked()) {
            sumber += "- Discord\n";
        }
        if (cbTwitch.isChecked()) {
            sumber += "- Twitch\n";
        }
        if (cbYoutube.isChecked()) {
            sumber += "- Youtube\n";
        }
        if (cbNimoTV.isChecked()) {
            sumber += "- NimoTV\n";
        }
        if (cbLainnya.isChecked()) {
            sumber += "- Lainnya\n";
        }

        return sumber;
    }

    // Method atau fungsi untuk mengambil data dari Radio Button yang tercheck
    // Method atau fungsi ini akan mengembalikan data berupa String
    private String getTurnamenSelected() {
        String turnamen = "";

        // Mengambil ID dari Radio Button yang ter check pada Radio Group
        int selectedId = rgTurnamen.getCheckedRadioButtonId();

        // Jika ID yang ter check sama dengan ID Radio button MLBB
        if (selectedId == rbMlbb.getId()) {
            // Maka Variabel turnamen berisi Mobile Legend Bang Bang
            turnamen = "Mobile Legend Bang Bang";
        } else if (selectedId == rbLolwr.getId()) {
            turnamen = "League of Legends Wild Rift";
        } else if (selectedId == rbFf.getId()) {
            turnamen = "Free Fire";
        } else if (selectedId == rbPubgm.getId()) {
            turnamen = "PUBG Mobile";
        } else if (selectedId == rbCodm.getId()) {
            turnamen = "Call of Duty Mobile";
        } else if (selectedId == rbValorant.getId()) {
            turnamen = "Valorant";
        }

        return turnamen;
    }

}