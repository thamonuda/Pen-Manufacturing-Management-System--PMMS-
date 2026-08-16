public class PermanentMarker extends Marker {

    public PermanentMarker(String productId, String name, double unitPrice) {
        super(productId, name, unitPrice);
    }

    @Override
    public double calculatePrice(int quantity) {
        return getUnitPrice() * quantity;
    }
}