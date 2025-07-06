public class Main {
    public static void main(String[] args) throws Exception {
        Product cheese = new ShippableProduct("Cheese", 100, 5, 0.2);
        Product biscuits = new ShippableProduct("Biscuits", 150, 3, 0.7);
        Product scratchCard = new Product("Scratch Card", 50, 10);

        Customer customer = new Customer("Walid", 500);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        CheckoutService.checkout(customer, cart);
    }
}