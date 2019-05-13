package com.example.firstapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ClientAPI.EmployeeAPI;
import model.EmployeeCUD;
import model.Employees;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDelete extends AppCompatActivity {
    private EditText etName, etSalary,etAge,etSearch;
    private Button btnSearch,btnUpdate,btnDelete;
   private  EmployeeAPI employeeAPI;
    private static final String BASE_URL="http://dummy.restapiexample.com/api/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        etSearch= findViewById(R.id.etSearchh);
        etName= findViewById(R.id.etNamee);
        etSalary= findViewById(R.id.etSalaryy);
        etAge= findViewById(R.id.etAgee);
        btnDelete= findViewById(R.id.btnDelete);
        btnSearch= findViewById(R.id.btnSearchh);
        btnUpdate= findViewById(R.id.btnUpdate);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateEmployee();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DeleteEmployee();
            }
        });




    }


    private void CreateInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
    }
    private void SearchData() {
        CreateInstance();
        Call<Employees> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etSearch.getText().toString()));
        listCall.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                etName.setText(response.body().getEmployee_name());
                etSalary.setText(response.body().getEmployee_salary());
                etAge.setText(response.body().getEmployee_salary());
            }
            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "ERROR" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

        private void UpdateEmployee() {
            CreateInstance();
            EmployeeCUD emp = new EmployeeCUD(etName.getText().toString(), Float.parseFloat(etSalary.getText().toString()), Integer.parseInt(etAge.getText().toString()));


            Call<Void> voidCall =  employeeAPI.updateEmployees(Integer.parseInt(etSearch.getText().toString()),emp);

            voidCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(UpdateDelete.this,"Update sucessfull" ,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(UpdateDelete.this,"ERROR" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });

    }

    private void DeleteEmployee(){

        CreateInstance();
        EmployeeCUD emp = new EmployeeCUD(etName.getText().toString(), Float.parseFloat(etSalary.getText().toString()), Integer.parseInt(etAge.getText().toString()));


        Call<Void> voidCall =  employeeAPI.deleteEmployee(Integer.parseInt(etSearch.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDelete.this,"Delete sucessfull" ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDelete.this,"ERROR" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }




}
