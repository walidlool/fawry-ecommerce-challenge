import java.util.List;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        for (Shippable item : items) {
            System.out.printf("1x %s 	%.0fg", item.getName(), item.getWeight() * 1000);
            totalWeight += item.getWeight();
        }
        System.out.printf("Total package weight %.1fkg", totalWeight);
    }
}