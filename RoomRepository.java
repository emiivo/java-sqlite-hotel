import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private final Connection connection;

    public RoomRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:rooms.db");
            connection.createStatement().executeUpdate("drop table if exists kambariai");
            connection.createStatement().executeUpdate("create table kambariai (id integer primary key autoincrement, pavadinimas string, aprasymas string, kaina real, arUzimtas boolean)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Room save(Room room) throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate(createInsertRoomSQL(room));
        return room;
    }

    public Room update(Room room) throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate(createUpdateRoomSQL(room));
        return room;
    }

    public List<Room> findAll() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from kambariai");
        List<Room> rooms = new ArrayList<>();
        while(rs.next())
        {
            System.out.println(rs.getInt("id") +  "\t" +
                    rs.getString("pavadinimas") + "\t" +
                    rs.getString("aprasymas") +"\t" +
                    rs.getInt("kaina") + "\t" +
                    rs.getBoolean("arUzimtas"));
        }

        return rooms;
    }


    public void delete(Room room) throws SQLException {
        connection.createStatement().executeUpdate("delete from kambariai where id = " + room.getId());
    }



    public Room getById(int id) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from kambariai where id = " + id);
        while(rs.next())
        {
            var roomId = rs.getInt("id");
            var roomName = rs.getString("pavadinimas");
            var roomDescription = rs.getString("aprasymas");
            var roomPrice = rs.getInt("kaina");
            var isRoomTaken = rs.getBoolean("arUzimtas");

            return new Room(roomId, roomName, roomDescription, roomPrice, isRoomTaken);
        }

        throw new RuntimeException("Kambarys nerastas");
    }

   /* public List<Room> kurieUzimti throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select arUzimtas from kambariai");
        List<Room> arJisUzimtas = new ArrayList<>();
        while(rs.next())
        {
            if(rs.getBoolean("arUzimtas") == true) {
                System.out.println(rs.getBoolean("arUzimtas"));
            }
        }

        return arJisUzimtas;
    }*/


    private String createUpdateRoomSQL(Room room) {
        return String.format(
                "UPDATE kambariai SET " +
                        "pavadinimas = \"%s\", " +
                        "aprasymas = \"%s\", " +
                        "kaina = %d, " +
                        "arUzimtas = \"%s\" " +
                        "WHERE id = %d",
                room.getName(),
                room.getDescription(),
                room.getPrice(),
                room.isTaken(),
                room.getId()
        );
    }

    private String createInsertRoomSQL(Room room) {
        return String.format(
                "INSERT INTO kambariai (pavadinimas, aprasymas, kaina, arUzimtas) VALUES(\"%s\", \"%s\", %d, %s)",
                room.getName(), room.getDescription(), room.getPrice(), room.isTaken()
        );
    }

}
