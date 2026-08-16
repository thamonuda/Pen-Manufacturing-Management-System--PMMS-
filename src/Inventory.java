import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Product> products;
    private Map<String, RawMaterial> rawMaterials;

    public Inventory() {
        products = new HashMap<>();
        rawMaterials = new HashMap<>();
    }

    public void addProduct(Product product)
            throws DuplicateProductIdException {

        if (products.containsKey(product.getProductId())) {
            throw new DuplicateProductIdException(
                    "Duplicate Product ID: " + product.getProductId()
            );
        }

        products.put(product.getProductId(), product);
        System.out.println("Product added successfully.");
    }

    public void addRawMaterial(RawMaterial material) {
        rawMaterials.put(material.getMaterialId(), material);
        System.out.println("Raw material added successfully.");
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public RawMaterial getRawMaterial(String materialId) {
        return rawMaterials.get(materialId);
    }

    public boolean isProductAvailable(String productId, int quantity) {
        Product product = products.get(productId);

        return product != null
                && product.getFinishedQuantity() >= quantity;
    }

    public void displayProducts() {
        System.out.println("\n--- FINISHED PRODUCTS ---");

        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    public void displayRawMaterials() {
        System.out.println("\n--- RAW MATERIALS ---");

        for (RawMaterial material : rawMaterials.values()) {
            System.out.println(material);
        }
    }

    public void displayAllInventory() {
        displayRawMaterials();
        displayProducts();
    }
}