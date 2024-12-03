
public class Payments {

    private int id;
    private int payment;
    private String payDescription;

    public Payments(int payment, String payDescription) {
        this.payment = payment;
        this.payDescription = payDescription;

    }

    public Payments(int id, int payment, String payDescription) {
        this.id = id;
        this.payment = payment;
        this.payDescription = payDescription;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment() {
        return this.payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public java.lang.String getPayDescription() {
        return this.payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription;
    }

    @Override
    public java.lang.String toString() {
        return "Payments{" +
                "id=" + id +
                ", payment=" + payment +
                ", payDescription=" + payDescription +
                '}';
    }
}
