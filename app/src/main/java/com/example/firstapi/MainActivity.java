package com.example.firstapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ClientAPI.EmployeeAPI;
import model.Employees;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL="http://dummy.restapiexample.com/api/v1/";
    private TextView tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate=findViewById(R.id.tvDate);

        loadDate();



    }

    private void loadDate() {

        // Retrofit ko instance bana ko
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Interface ko instance bana ko

        EmployeeAPI employeeAPI= retrofit.create(EmployeeAPI.class);

        Call<List<Employees>> listCall=employeeAPI.getAllEMployess();

        listCall.enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                List<Employees> employeesList= response.body();
                for(Employees em:employeesList){

                    String content="";

                    content+="ID :"+ em.getId()+ "\n";
                    content+=" Name :"+ em.getEmployee_name()+ "\n";
                    content+=" Age :"+ em.getEmployee_age()+ "\n";
                    content+=" Salary :"+ em.getEmployee_salary()+ "\n";
                    content+="---------------------------------"+"\n";

                    tvDate.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"ERROR" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}
