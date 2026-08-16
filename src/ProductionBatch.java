import java.util.HashMap;
import java.util.Map;

public class ProductionBatch {

    private String batchId;
    private Product product;
    private int plannedQuantity;
    private int producedQuantity;
    private String status;

    // Material ID + quantity needed for ONE product
    private Map<String, Double> materialRequirements;

    public ProductionBatch(String batchId, Product product,
                           int plannedQuantity) {

        this.batchId = batchId;
        this.product = product;
        this.plannedQuantity = plannedQuantity;
        this.producedQuantity = 0;
        this.status = "CREATED";
        this.materialRequirements = new HashMap<>();
    }

    public void addMaterialRequirement(String materialId,
                                       double quantityPerProduct) {
        materialRequirements.put(materialId, quantityPerProduct);
    }

    public Map<String, Double> getMaterialRequirements() {
        return materialRequirements;
    }

    public void startProduction() {
        status = "IN PROGRESS";
        System.out.println("Production started for batch: " + batchId);
    }

    public void completeProduction() {
        producedQuantity = plannedQuantity;
        status = "COMPLETED";

        product.addWaitingInspectionQuantity(producedQuantity);

        System.out.println("Production completed: "
                + producedQuantity + " products.");

        System.out.println("Send quantity to Quality Inspection: "
                + producedQuantity);
    }

    public String getBatchId() {
        return batchId;
    }

    public Product getProduct() {
        return product;
    }

    public int getPlannedQuantity() {
        return plannedQuantity;
    }

    public int getProducedQuantity() {
        return producedQuantity;
    }

    public String getStatus() {
        return status;
    }
}