public class Customer {

    private String customerId;
    private String name;
    private String address;
    private String telephone;

    public Customer(String customerId, String name,
                    String address, String telephone) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId
                + ", Name: " + name
                + ", Telephone: " + telephone;
    }
}