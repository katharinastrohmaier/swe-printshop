import java.util.Map;

public interface PrintShop
{
        void push(Order value) throws PrintShopException;

        Order pop() throws PrintShopException;

        public Map<String, Double> calcOrderDetails(int quantity, int actualDaysUntilShipped, double price, boolean isExpress) throws PrintShopException;

        public int calcDaysUntilShipping(int quantity, boolean isExpress) throws PrintShopException;

        void clear();

        int size();

}
