package ClientAPI;

import java.util.List;

import model.EmployeeCUD;
import model.Employees;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

//  To Get all the employees details
      @GET("employees")
      Call<List<Employees>> getAllEMployess();

      // Get employee on basic of employee id
      @GET("employee/{empID}")
      Call<Employees> getEmployeeByID(@Path("empID") int empID);

      // update employee on basis of emp id
      @PUT("employee/{empID}")
      Call<Void> updateEmployees(@Path("empID") int empId);

      // Add Employee to Web API
      @POST("create")
      Call<Void> registerEmployee(@Body EmployeeCUD emp);

      // Delete employee on the basic of emp id
      @DELETE("delete/{empID}")
      Call<Void> deleteEmployee(@Path ("empID") int empId);




}
