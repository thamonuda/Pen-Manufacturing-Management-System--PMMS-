import java.util.Map;

public class ProductionTask implements Runnable {

    private ProductionBatch batch;
    private Inventory inventory;

    public ProductionTask(ProductionBatch batch,
                          Inventory inventory) {
        this.batch = batch;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        try {
            System.out.println("Production thread started.");

            // Check every required raw material.
            for (Map.Entry<String, Double> entry
                    : batch.getMaterialRequirements().entrySet()) {

                String materialId = entry.getKey();
                double quantityPerProduct = entry.getValue();

                RawMaterial material =
                        inventory.getRawMaterial(materialId);

                double requiredQuantity =
                        quantityPerProduct * batch.getPlannedQuantity();

                if (material == null) {
                    System.out.println(
                            "Production cancelled: Raw material "
                                    + materialId + " not found."
                    );
                    return;
                }

                if (material.getAvailableQuantity()
                        < requiredQuantity) {

                    System.out.println(
                            "Production cancelled: Insufficient raw material."
                    );

                    System.out.println(
                            "Material: " + material.getMaterialName()
                    );

                    System.out.println(
                            "Required: " + requiredQuantity
                                    + " | Available: "
                                    + material.getAvailableQuantity()
                    );

                    return;
                }
            }

            // Materials are sufficient: reduce stock.
            for (Map.Entry<String, Double> entry
                    : batch.getMaterialRequirements().entrySet()) {

                RawMaterial material =
                        inventory.getRawMaterial(entry.getKey());

                double requiredQuantity =
                        entry.getValue()
                                * batch.getPlannedQuantity();

                material.reduceStock(requiredQuantity);
            }

            batch.startProduction();

            Thread.sleep(1000);

            batch.completeProduction();

        } catch (InsufficientRawMaterialException e) {
            System.out.println("Production Error: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("Production thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}