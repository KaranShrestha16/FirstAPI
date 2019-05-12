package model;

public class Employees {

    private int id;
    private String employee_name;
    private String employee_age;
    private String employee_salary;

    public Employees(int id, String employee_name, String employee_age, String employee_salary) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_age = employee_age;
        this.employee_salary = employee_salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(String employee_age) {
        this.employee_age = employee_age;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(String employee_salary) {
        this.employee_salary = employee_salary;
    }
}
