import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PrintShopImpl implements PrintShop {

    private Stack<Order> stack_ = new Stack<Order>();

    @Override
    public void push(Order value) throws PrintShopException
    {
        if(stack_.size() == 100){
            throw new PrintShopException("Quantity is greater then the maximum (100).");
        }
        else{
            stack_.push(value);
        }

    }

    @Override
    public int size()
    {
        return stack_.size();
    }

    @Override
    public Order pop() throws PrintShopException
    {
        if (stack_.isEmpty())
            throw new PrintShopException();
        return stack_.pop();
    }

    @Override
    public void clear() {
        stack_.clear();
    }

    @Override
    public Map<String, Double> calcOrderDetails(int quantity, int actualDaysUntilShipped, double price, boolean isExpress) throws PrintShopException {
        double discount = 0.0;
        double discountPercentage = 0.10;

        if(actualDaysUntilShipped < 0) {
            throw new PrintShopException("Days until shipped cant be negative!");
        }

        if(price < 0) {
            throw new PrintShopException("Price cant be negative!");
        }

        if (quantity > 100) {
            throw new PrintShopException("Quantity is greater then the maximum (100).");
        }

        if(quantity < 0) {
            throw new PrintShopException("Quantity can't be negative!");
        }

        if (isExpress) {
            discountPercentage = 0.15;
            price = price * 1.25;
        }

        int daysUntilShipping = calcDaysUntilShipping(quantity, isExpress);

        if (actualDaysUntilShipped > daysUntilShipping) {
            int shippingDaysDiff = actualDaysUntilShipped - daysUntilShipping;
            discount = calcDiscountForDelay(discountPercentage, shippingDaysDiff, price);

            if (discount > price) {
                discount = price;
            }
        }

        Map<String, Double> result = new HashMap<String, Double>();
        result.put("price", price);
        result.put("discount", discount);

        return result;
    }

    @Override
    public int calcDaysUntilShipping(int quantity, boolean isExpress) {
        int daysUntilShipping = 0;

        if (isExpress) {
            daysUntilShipping -= 1;
        }

        if (quantity > 0 && quantity < 5) {
            daysUntilShipping += 2;
        } else if (quantity >= 5 && quantity < 10) {
            daysUntilShipping += 3;
        } else if (quantity >= 10 && quantity <= 100) {
            daysUntilShipping += 5;
        }

        return daysUntilShipping;
    }

    private double calcDiscountForDelay(double discountPercentage, int delayedDays, double price) {
        return discountPercentage*delayedDays*price;
    }

}
