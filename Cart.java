import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) throws Exception {
        if (quantity > product.quantity)
            throw new Exception("Not enough stock");
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}