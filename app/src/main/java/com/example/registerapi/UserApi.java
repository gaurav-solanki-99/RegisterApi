package com.example.registerapi;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class UserApi
{
   public static UserApiInterface userApiInterface;

  public static UserApiInterface getUserApiInterface()
  {
      if(userApiInterface==null)
      {
          Retrofit retrofit = new Retrofit.Builder()
                  .baseUrl("https://dummy.restapiexample.com")
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();

          userApiInterface=retrofit.create(UserApiInterface.class);



      }

      return userApiInterface;
  }


}

interface  UserApiInterface
{
    @GET("/api/v1/employees")
    public Call<ResponseBody> getExpenseList();


    @FormUrlEncoded
    @POST("/api/v1/create")
    public Call<User2> userSave(@Field("name")String name,
                               @Field("age")int age,
                               @Field("salary")int salary);


    @DELETE("/api/v1/delete/{id}")
    Call<User2> deleteData(
            @Path("id") int id

    );


}
