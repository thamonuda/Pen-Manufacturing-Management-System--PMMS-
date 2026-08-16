public class QualityInspection {

    private String inspectionId;
    private int acceptedQuantity;
    private int defectiveQuantity;

    public QualityInspection(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    // inspectBatch(batch): void
    public void inspectBatch(ProductionBatch batch,
                             int acceptedQuantity,
                             int defectiveQuantity) {

        int producedQuantity = batch.getProducedQuantity();

        if (acceptedQuantity + defectiveQuantity != producedQuantity) {
            System.out.println(
                    "Error: Accepted + defective quantity must equal produced quantity."
            );
            return;
        }

        this.acceptedQuantity = acceptedQuantity;
        this.defectiveQuantity = defectiveQuantity;

        System.out.println("Batch inspected: " + batch.getBatchId());
        System.out.println("Accepted quantity: " + acceptedQuantity);
        System.out.println("Defective quantity: " + defectiveQuantity);
    }

    // updateInventory(): void
    public void updateInventory(ProductionBatch batch) {

        Product product = batch.getProduct();

        // Only accepted products enter finished-goods inventory.
        product.addFinishedQuantity(acceptedQuantity);
        product.addDefectiveQuantity(defectiveQuantity);
        product.clearWaitingInspectionQuantity();

        System.out.println("Finished inventory updated.");
        System.out.println("Finished quantity: "
                + product.getFinishedQuantity());
    }

    public String getInspectionId() {
        return inspectionId;
    }
}