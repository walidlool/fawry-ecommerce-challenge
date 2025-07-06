import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty())
            throw new Exception("Cart is empty");

        double subtotal = 0;
        List<Shippable> shippables = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();

            if (p.isExpired())
                throw new Exception(p.name + " is expired");
            if (p.quantity < qty)
                throw new Exception(p.name + " is out of stock");

            subtotal += p.price * qty;

            if (p.isShippable())
                shippables.add((Shippable) p);
        }

        double shipping = shippables.isEmpty() ? 0 : 30;
        double total = subtotal + shipping;

        if (!customer.canAfford(total))
            throw new Exception("Insufficient balance");

        customer.deduct(total);
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet())
            entry.getKey().quantity -= entry.getValue();

        if (!shippables.isEmpty())
            ShippingService.ship(shippables);

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%dx %s	%.0f
", entry.getValue(), entry.getKey().name, entry.getKey().price * entry.getValue());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal	" + subtotal);
        System.out.println("Shipping	" + shipping);
        System.out.println("Amount		" + total);
        System.out.println("Customer Balance	" + customer.balance);
    }
}