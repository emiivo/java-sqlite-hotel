import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserRepository {
    private final Connection connection;

    public UserRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:user.db");
            connection.createStatement().executeUpdate("drop table if exists vartotojai");
            connection.createStatement().executeUpdate("create table vartotojai (id integer primary key autoincrement, vardas string, pavarde string, gimimo_data string, kiekDienu integer)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User save(User user) throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate(createInsertUserSQL(user));
        return user;
    }
    public User update(User user) throws SQLException {
        var userStatement = connection.createStatement();
        userStatement.executeUpdate(createUpdateUserSQL(user));
        return user;
    }
    public List<User> findAll() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from vartotojai");
        List<User> users = new ArrayList<>();
        while(rs.next())
        {
            System.out.println(rs.getInt("id") +  "\t" +
                    rs.getString("vardas") + "\t" +
                    rs.getString("pavarde") +"\t" +
                    rs.getString("gimimo_data") + "\t" +
                    rs.getString("kiekDienu"));

        }

        return users;
    }
    public void delete(User user) throws SQLException {
        connection.createStatement().executeUpdate("delete from vartotojai where id = " + user.getId());
    }
    public User getById(int id) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from vartotojai where id = " + id);
        while (rs.next()) {
            var userId = rs.getInt("id");
            var userName = rs.getString("vardas");
            var userSurname = rs.getString("pavarde");
            var userDateOfBirth = rs.getString("gimimo_data");
            var nrOfDays = rs.getInt("kiekDienu");

            return new User(userId, userName, userSurname, userDateOfBirth, nrOfDays);
        }
        throw new RuntimeException("Vartotojas nerastas");
    }
    public User getByName(String name) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from vartotojai where vardas = " + name);
        while (rs.next()) {
            var userId = rs.getInt("id");
            var userName = rs.getString("vardas");
            var userSurname = rs.getString("pavarde");
            var userDateOfBirth = rs.getString("gimimo_data");
            var nrOfDays = rs.getInt("kiekDienu");

            return new User(userId, userName, userSurname, userDateOfBirth, nrOfDays);
        }
        throw new RuntimeException("Vartotojas nerastas");
    }
    public int bendraiPliuso() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select kiekDienu from vartotojai");
        int visoPliuso = 0;
        while(rs.next())
        {

            visoPliuso = visoPliuso + rs.getInt("kiekDienu");

        }

        return visoPliuso;
    }
    private String createUpdateUserSQL(User user) {
        return String.format(
                "UPDATE vartotojai SET " +
                        "vardas = \"%s\", " +
                        "pavarde = \"%s\", " +
                        "gimimo_data = \"%s\", " +
                        "kiekDienu = %d " +
                        "WHERE id = %d",
                user.getName(),
                user.getSurname(),
                user.getDateOfBirth(),
                user.getNumberOfDays(),
                user.getId()
        );
    }
    private String createInsertUserSQL(User user) {
        return String.format(
                "INSERT INTO vartotojai (vardas, pavarde, gimimo_data, kiekDienu) VALUES(\"%s\", \"%s\", \"%s\", %d)",
                user.getName(), user.getSurname(), user.getDateOfBirth(), user.getNumberOfDays()
        );
    }
}

