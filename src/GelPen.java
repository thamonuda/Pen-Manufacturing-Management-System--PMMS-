public class GelPen extends Product {

    public GelPen(String productId, String name, double unitPrice) {
        super(productId, name, unitPrice);
    }

    @Override
    public double calculatePrice(int quantity) {
        return getUnitPrice() * quantity;
    }
}