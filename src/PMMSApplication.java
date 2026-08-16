public class PMMSApplication {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        try {
            // Manager adds raw materials.
            inventory.addRawMaterial(
                    new RawMaterial("RM001", "Plastic Body", 5000)
            );

            inventory.addRawMaterial(
                    new RawMaterial("RM002", "Blue Ink", 200)
            );

            inventory.addRawMaterial(
                    new RawMaterial("RM003", "Pen Tip", 5000)
            );

            // Manager creates product.
            // Finished quantity starts at 0.
            Product ballpointPen = new BallpointPen(
                    "P001",
                    "Blue Ballpoint Pen",
                    50.00
            );

            inventory.addProduct(ballpointPen);

            // Production staff creates production batch for 100 pens.
            ProductionBatch batch = new ProductionBatch(
                    "B001",
                    ballpointPen,
                    100
            );

            // Materials needed to manufacture ONE ballpoint pen.
            batch.addMaterialRequirement("RM001", 1);
            batch.addMaterialRequirement("RM002", 2);
            batch.addMaterialRequirement("RM003", 1);

            QualityInspection inspection =
                    new QualityInspection("QI001");

            // Three tasks run concurrently.
            Thread productionThread = new Thread(
                    new ProductionTask(batch, inventory),
                    "Production-Thread"
            );

            Thread qualityThread = new Thread(
                    new QualityInspectionTask(
                            batch,
                            inspection,
                            95,
                            5
                    ),
                    "Quality-Thread"
            );

            Thread reportThread = new Thread(
                    new ReportTask(inventory),
                    "Report-Thread"
            );

            productionThread.start();
            qualityThread.start();
            reportThread.start();

            productionThread.join();
            qualityThread.join();
            reportThread.join();

            // Sales Staff: customer order.
            Customer customer = new Customer(
                    "C001",
                    "Kamal Stores",
                    "Colombo",
                    "0712345678"
            );

            SalesOrder order = new SalesOrder("O001", customer);

            // Customer requests 20 products.
            order.addOrderItem(new OrderItem(ballpointPen, 20));

            // Check product stock before confirming customer order.
            order.confirmOrder();

            // Generate invoice.
            Invoice invoice = new Invoice("INV001", order);
            invoice.generateInvoice();

            // Automatically reduce finished-product stock after invoice.
            order.updateInventoryAfterSale();

            System.out.println("\n--- FINAL INVENTORY ---");
            inventory.displayAllInventory();

        } catch (DuplicateProductIdException e) {
            System.out.println("Duplicate Product Error: "
                    + e.getMessage());

        } catch (InvalidOrderException e) {
            System.out.println("Customer Order Error: "
                    + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("Thread Error: "
                    + e.getMessage());

            Thread.currentThread().interrupt();
        }
    }
}