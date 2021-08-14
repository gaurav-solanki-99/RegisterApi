package com.example.registerapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registerapi.databinding.RegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity  extends AppCompatActivity
{

    RegisterBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=RegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());




        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =binding.etUsername.getText().toString();
                int age =Integer.parseInt(binding.etAge.getText().toString());
                int salary=Integer.parseInt(binding.etsalary.getText().toString());

                UserApiInterface userApiInterface=UserApi.getUserApiInterface();
                Call<User2> call= userApiInterface.userSave(name,age,salary);
                call.enqueue(new Callback<User2>() {
                    @Override
                    public void onResponse(Call<User2> call, Response<User2> response) {
                        System.out.println(">>>>>>>>>>>Response Code >>>"+response.code());
                        Log.e(" Code >>>",""+response.code());
                        if(response.code()==200)
                        {
                            Toast.makeText(RegisterActivity.this, "Data insert Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,RegisterActivity.class));
                           
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Data not insert", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User2> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        binding.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(binding.etId.getText().toString());

                UserApiInterface userApiInterface = UserApi.getUserApiInterface();
                Call<User2> call =userApiInterface.deleteData(id);
                call.enqueue(new Callback<User2>() {
                    @Override
                    public void onResponse(Call<User2> call, Response<User2> response) {
                        System.out.println(">>>>>>>>>>>Response Code >>>"+response.code());
                        Log.e(" Code >>>",""+response.code());

                        if(response.code()==200)
                        {
                            Toast.makeText(RegisterActivity.this, "Success Fully delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User2> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });



    }
}
