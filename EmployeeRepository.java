import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private final Connection connection;

    public EmployeeRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:employees.db");
            connection.createStatement().executeUpdate("drop table if exists darbuotojai");
            connection.createStatement().executeUpdate("create table darbuotojai (id integer primary key autoincrement, vardas string, pavarde string, alga integer)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee save(Employee employee) throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate(createInsertEmployeeSQL(employee));
        return employee;
    }

    public Employee update(Employee employee) throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate(createUpdateEmployeeSQL(employee));
        return employee;
    }

    public List<Employee> findAll() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from darbuotojai");
        List<Employee> employees = new ArrayList<>();
        while(rs.next())
        {
            System.out.println(rs.getInt("id") +  "\t" +
                    rs.getString("vardas") + "\t" +
                    rs.getString("pavarde") +"\t" +
                    rs.getInt("alga"));
        }

        return employees;
    }

    public void delete(Employee employee) throws SQLException {
        connection.createStatement().executeUpdate("delete from darbuotojai where id = " + employee.getId());
    }

    public Employee getById(int id) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from darbuotojai where id = " + id);
        while(rs.next())
        {
            var employeeId = rs.getInt("id");
            var employeeName = rs.getString("vardas");
            var employeeSurname = rs.getString("pavarde");
            var employeePaycheck = rs.getInt("alga");

            return new Employee(employeeId, employeeName, employeeSurname, employeePaycheck);
        }

        throw new RuntimeException("Darbuotojas nerastas");
    }

    private String createUpdateEmployeeSQL(Employee employee) {
        return String.format(
                "UPDATE darbuotojai SET " +
                        "vardas = \"%s\", " +
                        "pavarde = \"%s\", " +
                        "alga = %d " +
                        "WHERE id = %d",
                employee.getName(),
                employee.getSurname(),
                employee.getSalary(),
                employee.getId()
        );
    }
    private String createInsertEmployeeSQL(Employee employee) {
        return String.format(
                "INSERT INTO darbuotojai (vardas, pavarde, alga) VALUES(\"%s\", \"%s\", %d)",
                employee.getName(), employee.getSurname(), employee.getSalary()
        );
    }

}
