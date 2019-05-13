package com.example.firstapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ClientAPI.EmployeeAPI;
import model.EmployeeCUD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeRegistration extends AppCompatActivity {

    private final static String BASE_URL ="http://dummy.restapiexample.com/api/v1/";
    private EditText etName, etSalary,etAge;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        etAge=findViewById(R.id.etAge);
        etSalary=findViewById(R.id.etSalary);
        etName=findViewById(R.id.etName);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Register();
            }
        });
    }

    private void Register(){
        EmployeeCUD emp= new EmployeeCUD(etName.getText().toString(),Float.parseFloat(etSalary.getText().toString()),Integer.parseInt(etAge.getText().toString()));

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.registerEmployee(emp);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(EmployeeRegistration.this,"You have been sucessfully registered" ,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(EmployeeRegistration.this,"ERROR" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
