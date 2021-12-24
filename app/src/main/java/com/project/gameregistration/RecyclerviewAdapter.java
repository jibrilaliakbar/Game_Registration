package com.project.gameregistration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Ini merupakan class pengaturan untuk recycler viewnya
// Terutama Adapter nya
public class RecyclerviewAdapter extends RecyclerView.Adapter<UserViewHolder> {
    List<User> userList;

    public RecyclerviewAdapter(List<User> userList) {
        this.userList = userList;
    }

    // Meng set view holder nya
    // Atau bisa dibilang tampilan row atau baris nya
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    // Mengset isi tiap baris datanya
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.txtNamaLengkap.setText(userList.get(position).getNamaLengkap());
        holder.txtNickname.setText(userList.get(position).getNickname());
        holder.txtTurnament.setText(userList.get(position).getTurnament());
    }

    // Ini untuk menghitung jumlah datanya
    @Override
    public int getItemCount() {
        return userList.size();
    }
}
