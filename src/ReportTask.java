public class ReportTask implements Runnable {

    private Inventory inventory;

    public ReportTask(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);

            System.out.println("\n--- REPORT THREAD ---");
            inventory.displayAllInventory();

        } catch (InterruptedException e) {
            System.out.println("Report thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}