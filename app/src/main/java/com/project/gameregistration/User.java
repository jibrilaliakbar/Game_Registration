package com.project.gameregistration;

// Ini merupakan model dari si User
public class User {
    // Pendeklarasian tiap Variabelnya
    private int id;
    private String namaLengkap;
    private String nickname;
    private String email;
    private String domisili;
    private String turnament;
    private String sumber;
    private String rating;

    // Method setter dan getter untuk bisa memakai si variabelnya
    // Karena tipenya private ( untuk keamanan data, yang dimana variabel dengan tipe ini hanya bisa diakses di class ini saja )
    // Maka diakses nya tidak langsung memakai variabelnya
    // Melainkan memakai fungsi setter dan getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomisili() {
        return domisili;
    }

    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public String getTurnament() {
        return turnament;
    }

    public void setTurnament(String turnament) {
        this.turnament = turnament;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
