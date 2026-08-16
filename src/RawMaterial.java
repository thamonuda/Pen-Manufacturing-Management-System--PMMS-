public class RawMaterial {

    private String materialId;
    private String materialName;
    private double availableQuantity;

    public RawMaterial(String materialId, String materialName,
                       double availableQuantity) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.availableQuantity = availableQuantity;
    }

    public String getMaterialId() {
        return materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public double getAvailableQuantity() {
        return availableQuantity;
    }

    public void addStock(double quantity) {
        availableQuantity += quantity;
    }

    public void reduceStock(double quantity)
            throws InsufficientRawMaterialException {

        if (quantity > availableQuantity) {
            throw new InsufficientRawMaterialException(
                    "Insufficient raw material: " + materialName
            );
        }

        availableQuantity -= quantity;
    }

    @Override
    public String toString() {
        return materialId + " | " + materialName
                + " | Quantity: " + availableQuantity;
    }
}