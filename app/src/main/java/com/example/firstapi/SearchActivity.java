package com.example.firstapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private TextView tvData;
    private Button btnSearch;
    private static final String BASE_URL="http://dummy.restapiexample.com/api/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch=findViewById(R.id.etSearch);
        btnSearch=findViewById(R.id.btnSearch);
        tvData=findViewById(R.id.tvData);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Validation()== true){

                    loadDate();
                }
            }


        });
    }

    private boolean Validation() {
        return true;
    }

    private void loadDate() {
        // Retrofit ko instance bana ko
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Interface ko instance bana ko

        EmployeeAPI employeeAPI= retrofit.create(EmployeeAPI.class);

        Call<Employees> listCall=employeeAPI.getEmployeeByID(Integer.parseInt(etSearch.getText().toString()));
        listCall.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                Toast.makeText(SearchActivity.this,response.body().toString() ,Toast.LENGTH_LONG).show();
                String content="";

                content+="ID :"+ response.body().getId()+ "\n";
                content+=" Name :"+ response.body().getEmployee_name()+ "\n";
                content+=" Age :"+ response.body().getEmployee_age()+ "\n";
                content+=" Salary :"+ response.body().getEmployee_salary()+ "\n";
                content+="---------------------------------"+"\n";
                tvData.setText(content);
            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                Toast.makeText(SearchActivity.this,"ERROR" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

}
