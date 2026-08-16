import java.util.ArrayList;
import java.util.List;

public class SalesOrder {

    private String orderId;
    private Customer customer;
    private List<OrderItem> orderItems;
    private String status;

    public SalesOrder(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderItems = new ArrayList<>();
        this.status = "PENDING";
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }

    public double calculateTotal() {
        double total = 0;

        for (OrderItem item : orderItems) {
            total += item.calculateSubTotal();
        }

        return total;
    }

    public void confirmOrder() throws InvalidOrderException {

        if (orderItems.isEmpty()) {
            throw new InvalidOrderException("Customer order is empty.");
        }

        // Check stock before reducing product quantities.
        for (OrderItem item : orderItems) {
            if (item.getQuantity() > item.getProduct().getFinishedQuantity()) {
                throw new InvalidOrderException(
                        "Customer requested quantity is not available for: "
                                + item.getProduct().getName()
                );
            }
        }

        status = "CONFIRMED";
    }

    // Call after invoice generation.
    public void updateInventoryAfterSale()
            throws InvalidOrderException {

        for (OrderItem item : orderItems) {
            item.getProduct().reduceFinishedQuantity(item.getQuantity());
        }

        System.out.println("Product quantity updated after sale.");
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getStatus() {
        return status;
    }
}