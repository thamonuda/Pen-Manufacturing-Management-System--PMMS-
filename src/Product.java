public abstract class Product {

    private String productId;
    private String name;
    private double unitPrice;

    // Finished-goods quantity starts from 0.
    private int finishedQuantity;

    // Manufactured products waiting for quality inspection.
    private int waitingInspectionQuantity;

    private int defectiveQuantity;

    public Product(String productId, String name, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.finishedQuantity = 0;
        this.waitingInspectionQuantity = 0;
        this.defectiveQuantity = 0;
    }

    // Polymorphism: each product type can calculate price differently.
    public abstract double calculatePrice(int quantity);

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getFinishedQuantity() {
        return finishedQuantity;
    }

    public int getWaitingInspectionQuantity() {
        return waitingInspectionQuantity;
    }

    public int getDefectiveQuantity() {
        return defectiveQuantity;
    }

    // Quality-passed products enter finished-goods inventory.
    public void addFinishedQuantity(int quantity) {
        finishedQuantity += quantity;
    }

    // Manufactured products are sent to Quality Inspection.
    public void addWaitingInspectionQuantity(int quantity) {
        waitingInspectionQuantity += quantity;
    }

    public void clearWaitingInspectionQuantity() {
        waitingInspectionQuantity = 0;
    }

    public void addDefectiveQuantity(int quantity) {
        defectiveQuantity += quantity;
    }

    // Called after invoice generation.
    public void reduceFinishedQuantity(int quantity)
            throws InvalidOrderException {

        if (quantity <= 0 || quantity > finishedQuantity) {
            throw new InvalidOrderException(
                    "Insufficient finished-goods quantity."
            );
        }

        finishedQuantity -= quantity;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId
                + ", Name: " + name
                + ", Price: Rs. " + unitPrice
                + ", Finished Qty: " + finishedQuantity
                + ", Waiting Inspection: " + waitingInspectionQuantity;
    }
}