package com.project.gameregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ModifyActivity extends AppCompatActivity {
    private EditText edtNamaLengkap;
    private EditText edtNickname;
    private EditText edtEmail;
    private EditText edtDomisili;
    private RadioGroup rgTurnament;
    private RadioButton rbMlbb;
    private RadioButton rbLolwr;
    private RadioButton rbFf;
    private RadioButton rbPubgm;
    private RadioButton rbCodm;
    private RadioButton rbValorant;
    private CheckBox cbInstagram;
    private CheckBox cbDiscord;
    private CheckBox cbTwitch;
    private CheckBox cbYoutube;
    private CheckBox cbNimoTV;
    private CheckBox cbLainnya;
    private SeekBar seekBar;
    private TextView txtRating;
    private int id;
    private String namaLengkap;
    private String nickname;
    private String email;
    private String domisili;
    private String turnament;
    private String sumber;
    private String rating;
    private Button btnUbah;

    private DatabaseHelper db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        edtNamaLengkap = findViewById(R.id.input_namalengkap);
        edtNickname = findViewById(R.id.input_nickname);
        edtEmail = findViewById(R.id.input_email);
        edtDomisili = findViewById(R.id.input_domisili);
        rgTurnament = findViewById(R.id.rgturnamen);
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
        btnUbah = findViewById(R.id.ubah);

        db = new DatabaseHelper(this);

        // Mengambil data dari Intent putExtra
        // Yang dikirim activity sebelumnya
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        namaLengkap = intent.getExtras().getString("namaLengkap");
        nickname = intent.getExtras().getString("nickname");
        email = intent.getExtras().getString("email");
        domisili = intent.getExtras().getString("domisili");
        turnament = intent.getExtras().getString("turnament");
        sumber = intent.getExtras().getString("sumber");
        rating = intent.getExtras().getString("rating");

        // Lalu di set di formnya
        edtNamaLengkap.setText(namaLengkap);
        edtNickname.setText(nickname);
        edtEmail.setText(email);
        edtDomisili.setText(domisili);
        txtRating.setText("Beri nilai tim kami : " + rating);
        setTurnamentSelected();
        setSumberSelected();
        seekBar.setProgress(Integer.parseInt(rating));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rating = String.valueOf(i);
                txtRating.setText("Beri nilai tim kami : " + rating);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaLengkap = edtNamaLengkap.getText().toString().trim();
                nickname = edtNickname.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                domisili = edtDomisili.getText().toString().trim();
                turnament = getTurnamenSelected();
                sumber = getSumberSelected();

                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
                builder.setIcon(R.drawable.warning);
                builder.setTitle("Daftarkan");

                builder.setMessage(
                        "Apakah anda sudah yakin dengan data anda ?\n\n" +
                                "Nama Lengkap : \n" + namaLengkap + "\n\n" +
                                "Nickname : \n" + nickname + "\n\n" +
                                "Email : \n" + email + "\n\n" +
                                "Domisili : \n" + domisili + "\n\n" +
                                "Turnament : \n" + turnament + "\n\n" +
                                "Sumber : \n" + sumber + "\n\n" +
                                "Rating : \n" + rating + ""
                );

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent layoutMenu = new Intent(ModifyActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        layoutMenu.putExtra("status", "edit");

                        user = new User();
                        user.setId(id);
                        user.setNamaLengkap(namaLengkap);
                        user.setNickname(nickname);
                        user.setEmail(email);
                        user.setDomisili(domisili);
                        user.setTurnament(turnament);
                        user.setSumber(sumber);
                        user.setRating(rating);

                        // Memanggil fungsi update database
                        db.update(user);

                        startActivity(layoutMenu);
                        finish();
                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    // Untuk meng set data combo box sesuai dengan data dari putExtra atau database
    private void setSumberSelected() {
        // Apabila variabel sumber ada kata tertentu didalamnya
        // Maka cb tertentu akan ter checked
        if (sumber.contains("Instagram")) {
            cbInstagram.setChecked(true);
        }
        if (sumber.contains("Discord")) {
            cbDiscord.setChecked(true);
        }
        if (sumber.contains("Twitch")) {
            cbTwitch.setChecked(true);
        }
        if (sumber.contains("Youtube")) {
            cbYoutube.setChecked(true);
        }
        if (sumber.contains("NimoTV")) {
            cbNimoTV.setChecked(true);
        }
        if (sumber.contains("Lainnya")) {
            cbLainnya.setChecked(true);
        }
    }

    private String getSumberSelected() {
        String sumber = "";

        if (cbInstagram.isChecked()) {
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

    // Meng set radio button yang pilih sesuai dengan data dari putExtra atau database
    private void setTurnamentSelected() {
        if (turnament.equals("Mobile Legend Bang Bang")) {
            rbMlbb.setChecked(true);
        } else if (turnament.equals("League of Legends Wild Rift")) {
            rbLolwr.setChecked(true);
        } else if (turnament.equals("Free Fire")) {
            rbFf.setChecked(true);
        } else if (turnament.equals("PUBG Mobile")) {
            rbPubgm.setChecked(true);
        } else if (turnament.equals("Call of Duty Mobile")) {
            rbCodm.setChecked(true);
        } else if (turnament.equals("Valorant")) {
            rbValorant.setChecked(true);
        }
    }

    private String getTurnamenSelected() {
        String turnamen = "";

        int selectedId = rgTurnament.getCheckedRadioButtonId();

        if (selectedId == rbMlbb.getId()) {
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