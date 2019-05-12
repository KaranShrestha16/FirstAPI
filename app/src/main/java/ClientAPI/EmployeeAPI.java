package ClientAPI;

import java.util.List;
import model.Employees;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {

      @GET("employees")
      Call<List<Employees>> getAllEMployess();

      @GET("employee/{empID}")
      Call<Employees> getEmployeeByID(@Path("empID") int empID);
}
