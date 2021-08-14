package com.example.registerapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.registerapi.databinding.EmployessLayoutBinding;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    EmployessLayoutBinding binding;
    ArrayList<User> al;

    UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=EmployessLayoutBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

//       UserApiInterface userApiInterface= UserApi.getUserApiInterface();
//
//      userApiInterface.getExpenseList().enqueue(new Callback<ResponseBody>() {
//          @Override
//          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//              ProgressDialog pd = new ProgressDialog(MainActivity.this);
//              pd.setMessage("Loading");
//              pd.show();
//
//              String abc="";
//
//
//                  al=new ArrayList<>();
//
//                  try {
//                      System.out.println("Json >>>>" + response.body().string());
////                       abc = ""+response.body().string();
//
//                       System.out.println("URL >>>>>>"+response.body().string());
//
////                      JSONObject obj = new JSONObject("{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"Garrett Winters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"Ashton Cox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"Cedric Kelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"Airi Satou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"Brielle Williamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"Herrod Chandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"Rhona Davidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"Colleen Hurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"Sonya Frost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"Jena Gaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"Quinn Flynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"Charde Marshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"Haley Kennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"Tatyana Fitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"Michael Silva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"Paul Byrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"Gloria Little\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"Bradley Greer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"Dai Rios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"Jenette Caldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"Yuri Berry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"Caesar Vance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"Doris Wilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully! All records has been fetched.\"}");
//                    JSONObject obj = new JSONObject(response.body().string());
//
//                      JSONArray arr = obj.getJSONArray("data");
//
//
//
//                      for (int i = 0; i < arr.length(); i++) {
//                          JSONObject obj2 = arr.getJSONObject(i);
//                          String name = obj2.getString("employee_name");
//                          String age = ""+ obj2.getInt("employee_age");
//                          String salary = ""+obj2.getInt("employee_salary");
//                          al.add(new User(name, age, salary));
//                      }
//                      pd.dismiss();
//
//                      adapter = new UserAdapter(MainActivity.this, al);
//                      binding.rv.setAdapter(adapter);
//                      binding.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//
//
//                  } catch (Exception e) {
//                      e.printStackTrace();
//                      pd.dismiss();
//
//                      System.out.println("Exceptiopn Parsing  >>> "+e.getMessage());
//                  }
//
//
//                  pd.dismiss();
//
//                  System.out.println("Response Code >>>>>"+response.code());
//                  Toast.makeText(MainActivity.this, "response Code "+response.code(), Toast.LENGTH_SHORT).show();
//
//
//          }
//
//          @Override
//          public void onFailure(Call<ResponseBody> call, Throwable t) {
//              System.out.println("Error  >>>>"+t.getMessage());
//
//          }
//      });

        String apiurl = "http://dummy.restapiexample.com/api/v1/employees";

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loding Employee List");
        pd.show();


        StringRequest request = new StringRequest(Request.Method.GET, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                try {

                    al=new ArrayList<>();
                    JSONObject obj = new JSONObject(response);

                    JSONArray arr = obj.getJSONArray("data");


                    //  Toast.makeText(MainActivity.this, ""+arr.getJSONObject(0), Toast.LENGTH_SHORT).show();

                    for(int i=0;i<arr.length();i++)
                    {
                        JSONObject obj2 = arr.getJSONObject(i);

                        String name = obj2.getString("employee_name");
                        String salary=obj2.getString("employee_salary");
                        int id = Integer.parseInt(obj2.getString("id"));

                        User obj3 = new User();
                        obj3.setName(name);
                        obj3.setSalary(salary);

                        al.add(obj3);
                        adapter= new UserAdapter(MainActivity.this,al);
                        binding.rv.setAdapter(adapter);
                        binding.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    }







                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }
}