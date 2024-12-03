
public class Room {

    private int id;
    private String name;
    private String description;
    private int price;
    private boolean isTaken;

    public Room(String name, String description, int price, boolean isTaken) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isTaken = isTaken;
    }

    public Room(int id, String name, String description, int price, boolean isTaken) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isTaken = isTaken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isTaken() {
        return this.isTaken;
    }

    public void setTaken(boolean taken) { this.isTaken = taken; }



    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isTaken=" + isTaken +
                '}';
    }
}
