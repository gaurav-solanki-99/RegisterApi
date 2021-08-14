package com.example.registerapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registerapi.databinding.SingleUserBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{
   Context context;
   ArrayList<User> al;

   public  UserAdapter(Context context,ArrayList<User> al)
   {
       this.context=context;
       this.al=al;
   }

    @NonNull

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
    SingleUserBinding binding = SingleUserBinding.inflate(LayoutInflater.from(context),parent,false);


        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserAdapter.UserViewHolder holder, int position) {

       User user = al.get(position);

       holder.binding.tvUsername.setText(user.getName());
       holder.binding.tvUserAge.setText(user.getAge());
       holder.binding.tvUserSalary.setText(user.getSalary());

    }

    @Override
    public int getItemCount() {
        return al.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder
   {
       SingleUserBinding binding;

       public UserViewHolder(SingleUserBinding binding)
       {
           super(binding.getRoot());
           this.binding=binding;
       }
   }



}
