public class Invoice {

    private String invoiceId;
    private SalesOrder salesOrder;

    public Invoice(String invoiceId, SalesOrder salesOrder) {
        this.invoiceId = invoiceId;
        this.salesOrder = salesOrder;
    }

    public void generateInvoice() {
        System.out.println("\n===== INVOICE =====");
        System.out.println("Invoice ID: " + invoiceId);
        System.out.println("Customer: " + salesOrder.getCustomer().getName());
        System.out.println("Order ID: " + salesOrder.getOrderId());

        for (OrderItem item : salesOrder.getOrderItems()) {
            System.out.println(item);
        }

        System.out.println("Total Amount: Rs. "
                + salesOrder.calculateTotal());
        System.out.println("===================");
    }
}