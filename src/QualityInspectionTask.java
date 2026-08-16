public class QualityInspectionTask implements Runnable {

    private ProductionBatch batch;
    private QualityInspection inspection;
    private int acceptedQuantity;
    private int defectiveQuantity;

    public QualityInspectionTask(ProductionBatch batch,
                                 QualityInspection inspection,
                                 int acceptedQuantity,
                                 int defectiveQuantity) {
        this.batch = batch;
        this.inspection = inspection;
        this.acceptedQuantity = acceptedQuantity;
        this.defectiveQuantity = defectiveQuantity;
    }

    @Override
    public void run() {
        try {
            // Wait until production is completed.
            while (!batch.getStatus().equals("COMPLETED")) {
                Thread.sleep(200);
            }

            System.out.println("Quality inspection thread started.");

            inspection.inspectBatch(
                    batch, acceptedQuantity, defectiveQuantity
            );

            inspection.updateInventory(batch);

        } catch (InterruptedException e) {
            System.out.println("Quality inspection thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}