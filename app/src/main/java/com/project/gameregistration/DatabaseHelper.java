package com.project.gameregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

// Ini merupakan Class untuk membantu kita mengelola database
// Class ini memakai metode inheritance / pewarisan
// Dia meng-extends ke cLass SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {
    // Pendeklarasian dan Inisialisasi Variabel
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "GameRegistration";
    private static final String TABLE_NAME = "tbl_user";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAMALENGKAP = "nama_lengkap";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DOMISILI = "domisili";
    private static final String KEY_TURNAMENT = "turnament";
    private static final String KEY_SUMBER = "sumber";
    private static final String KEY_RATING = "rating";

    // Ini adalah Constructor
    // Yaitu fungsi yang akan dijalankan pertama kali saat class ini dipanggil/dibuat
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Fungsi Override dari SQLiteOpenHelper yang didapat dari hasil pewarisan
    // Didalam fungsi ini saya isikan untuk membuat tabel di database nya
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME +
                " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAMALENGKAP + " TEXT, " +
                KEY_NICKNAME + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_DOMISILI + " TEXT, " +
                KEY_TURNAMENT + " TEXT, " +
                KEY_SUMBER + " TEXT, " +
                KEY_RATING + " TEXT " + ")";
        sqLiteDatabase.execSQL(createUserTable);
    }

    // Fungsi Override dari SQLiteOpenHelper yang didapat dari hasil pewarisan
    // Didalam fungsi ini saya isikan untuk menghapus tabel apabila sudah ada, lalu membuatnya kembali
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    // Ini merupakan fungsi untuk insert data ke database
    public void insert (User user) {
        // Deklarasi dan Inisialisasi
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // Memasukkan data ke dalam ContentValues yang digunakan untuk ke db nya nanti
        values.put(KEY_NAMALENGKAP, user.getNamaLengkap());
        values.put(KEY_NICKNAME, user.getNickname());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_DOMISILI, user.getDomisili());
        values.put(KEY_TURNAMENT, user.getTurnament());
        values.put(KEY_SUMBER, user.getSumber());
        values.put(KEY_RATING, user.getRating());

        // Memanggil fungsi insert dari SQLiteDatabase yang memiliki
        // Parameter nama tabel, nullColumnHack, dan value yang mau ditambah ke tabel itu
        db.insert(TABLE_NAME, null, values);
    }

    // Fungsi untuk Read data di database
    // Yang akan mengembalikan ( me-return ) data berupa List
    public List<User> selectUserData() {
        // Deklarasi dan Inisialisasi
        ArrayList<User> users = new ArrayList<User>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_ID, KEY_NAMALENGKAP, KEY_NICKNAME, KEY_EMAIL, KEY_DOMISILI, KEY_TURNAMENT, KEY_SUMBER, KEY_RATING};

        // Dengan Cursor ini kita akan mengambil datanya
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        // Cursor akan membaca baris tiap baris data
        // Apabila selanjutnya masih ada data maka perulangan ini akan berlanjut
        // Dan apabila sudah tidak ada data maka perulangan ini akan berhenti
        while (cursor.moveToNext()) {
            // Meng-set data dari cursor ke dalam sebuah variabel
            int id = cursor.getInt(0);
            String namaLengkap = cursor.getString(1);
            String nickname = cursor.getString(2);
            String email = cursor.getString(3);
            String domisili = cursor.getString(4);
            String turnament = cursor.getString(5);
            String sumber = cursor.getString(6);
            String rating = cursor.getString(7);

            // Lalu datanya di set kesebuah model
            User user = new User();
            user.setId(id);
            user.setNamaLengkap(namaLengkap);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setDomisili(domisili);
            user.setTurnament(turnament);
            user.setSumber(sumber);
            user.setRating(rating);

            // Dan dimasukkan ke dalam List
            users.add(user);
        }

        // Lalu list nya di return
        return users;
    }

    // Fungsi untuk mengupdate data di database
    public void update(User user) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMALENGKAP, user.getNamaLengkap());
        values.put(KEY_NICKNAME, user.getNickname());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_DOMISILI, user.getDomisili());
        values.put(KEY_TURNAMENT, user.getTurnament());
        values.put(KEY_SUMBER, user.getSumber());
        values.put(KEY_RATING, user.getRating());

        // Karena ini fungsi update maka membutuhkan whereClause
        // Yaitu data mana yang mau diedit, biasanya mengambil id nya
        // Karena id biasanya valuenya primary atau tiap data pasti memiliki id yang beda
        String whereClause = KEY_ID + " = '" + user.getId() + "'";

        db.update(TABLE_NAME, values, whereClause, null);
    }

    // Fungsi untuk menghapus data di database
    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        // Seperti fungsi edit fungsi delete pun membutuhkan whereClause
        // Data dengan id mana yang mau di hapus
        String whereClause = KEY_ID + " = '" + id + "'";

        db.delete(TABLE_NAME, whereClause, null);
    }
}
