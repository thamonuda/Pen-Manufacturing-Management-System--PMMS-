public class BallpointPen extends Product {

    public BallpointPen(String productId, String name, double unitPrice) {
        super(productId, name, unitPrice);
    }

    @Override
    public double calculatePrice(int quantity) {
        return getUnitPrice() * quantity;
    }
}