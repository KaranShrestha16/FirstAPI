package ClientAPI;

import java.util.List;
import model.Employees;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeAPI {

      @GET("employees")
      Call<List<Employees>> getAllEMployess();

}
