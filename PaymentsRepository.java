import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class PaymentsRepository {

    private final Connection connection;


    public PaymentsRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:payments.db");
            connection.createStatement().executeUpdate("drop table if exists mokejimai");
            connection.createStatement().executeUpdate("create table mokejimai (id integer primary key autoincrement, mokejimas integer, aprasymas string)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Payments save(Payments payments) throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate(createInsertPaymentsSQL(payments));
        return payments;
    }
    public Payments update(Payments payments) throws SQLException {
        var userStatement = connection.createStatement();
        userStatement.executeUpdate(createUpdatePaymentsSQL(payments));
        return payments;
    }
    public List<Payments> findAll() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from mokejimai");
        List<Payments> payments = new ArrayList<>();
        while(rs.next())
        {
            System.out.println(rs.getInt("id") +  "\t" +
                        rs.getInt("mokejimas") + "\t" +
                        rs.getString("aprasymas"));
        }

        return payments;
    }

    public int bendraiMokejimu() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select mokejimas from mokejimai");
        int bendriMokejimai = 0;
        while(rs.next())
        {

            bendriMokejimai = bendriMokejimai + rs.getInt("mokejimas");

        }

        return bendriMokejimai;
    }
    public void delete(Payments payments) throws SQLException {
        connection.createStatement().executeUpdate("delete from mokejimai where id = " + payments.getId());
    }
    public Payments getById(int id) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from mokejimai where id = " + id);
        while (rs.next()) {
            var paymentId = rs.getInt("id");
            var paymentAmount = rs.getInt("mokejimas");
            var paymentDescription = rs.getString("aprasymas");

            return new Payments(paymentId, paymentAmount, paymentDescription);
        }
        throw new RuntimeException("Mokejimas nerastas");
    }
    private String createUpdatePaymentsSQL(Payments payments) {
        return String.format(
                "UPDATE mokejimai SET " +
                        "mokejimas = %d, " +
                        "aprasymas = \"%s\"" +
                        "WHERE id = %d",
                payments.getPayment(),
                payments.getPayDescription(),
                payments.getId()
        );
    }
    private String createInsertPaymentsSQL(Payments payments) {
        return String.format(
                "INSERT INTO mokejimai (mokejimas, aprasymas) VALUES(%d, \"%s\")",
                payments.getPayment(), payments.getPayDescription()
        );
    }
}

