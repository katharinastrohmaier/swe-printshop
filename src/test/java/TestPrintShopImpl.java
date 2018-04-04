import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPrintShopImpl
{
    private Map<String, Double> result;

    @Before
    public void init() {
        result = new HashMap<String, Double>();
    }

    @Test
    public void calcDaysUntilShippingFunctionPTF1() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 3)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        daysuntilShipping = printShopImpl.calcDaysUntilShipping(printShopImpl.size(), true);
        assertEquals(daysuntilShipping, 1);
    }

    @Test
    public void calcDaysUntilShippingFunctionPTF2() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 7)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        daysuntilShipping = printShopImpl.calcDaysUntilShipping(printShopImpl.size(), true);
        assertEquals(daysuntilShipping, 2);
    }

    @Test
    public void calcDaysUntilShippingFunctionPTF3() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 99)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        daysuntilShipping = printShopImpl.calcDaysUntilShipping(printShopImpl.size(), true);
        assertEquals(daysuntilShipping, 4);
    }

    @Test
    public void calcDaysUntilShippingFunctionPTF4() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 3)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        daysuntilShipping = printShopImpl.calcDaysUntilShipping(printShopImpl.size(), false);
        assertEquals(daysuntilShipping, 2);
    }

    @Test
    public void calcDaysUntilShippingFunctionPTF5() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 7)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        daysuntilShipping = printShopImpl.calcDaysUntilShipping(printShopImpl.size(), false);
        assertEquals(daysuntilShipping, 3);
    }

    @Test
    public void calcDaysUntilShippingFunctionPTF6() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 99)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        daysuntilShipping = printShopImpl.calcDaysUntilShipping(printShopImpl.size(), false);
        assertEquals(daysuntilShipping, 5);
    }

    @Test
    public void calcDaysUntilShippingFunctionNTF1() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        try
        {
            daysuntilShipping = printShopImpl.calcDaysUntilShipping(-3, true);
        }
        catch(PrintShopException e) {
            assertEquals("Quantity can't be negative!", e.getMessage());
        }
    }

    @Test
    public void calcDaysUntilShippingFunctionNTF2() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int daysuntilShipping = 0;

        try
        {
            daysuntilShipping = printShopImpl.calcDaysUntilShipping(100030303, true);
        }
        catch(PrintShopException e) {
            assertEquals("Quantity is greater then the maximum (100).", e.getMessage());
        }
    }

    @Test
    public void calcFunctionShouldThrowExceptionIfQuantityIsAbove100() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();

        try
        {
            result = printShopImpl.calcOrderDetails(111, 5, 200, true);
            fail("Exception expected");
        }
        catch(PrintShopException e) {
            assertEquals("Quantity is greater then the maximum (100).", e.getMessage());
        }
    }

    @Test
    public void calcFunctionShouldNotAllowNegativePrice() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 5)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        try
        {
            result = printShopImpl.calcOrderDetails(printShopImpl.size(), 100, -200, false);
            fail("Exception expected");
        }
        catch (PrintShopException e)
        {
            assertEquals("Price cant be negative!", e.getMessage());
        }
    }

    @Test
    public void calcFunctionShouldNotAllowNegativeDaysUntilShipped() throws Exception
    {
        PrintShop printShopImpl = new PrintShopImpl();
        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 5)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }
        try
        {
            result = printShopImpl.calcOrderDetails(printShopImpl.size(), -100, 200, false);
            fail("Exception expected");
        }
        catch (PrintShopException e){
            assertEquals("Days until shipped cant be negative!", e.getMessage());
        }
    }

    @Test
    public void calcFunctionShouldNotThrowExceptionIfQuantityIsUnderneath100() throws PrintShopException {
        PrintShop printShopImpl = new PrintShopImpl();
        Double expectedPrice = 200.0;
        Double expectedDiscount = 0.0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 99)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        result = printShopImpl.calcOrderDetails(printShopImpl.size(), 5, 200, false);
        assertEquals(expectedPrice, result.get("price"));
        assertEquals(expectedDiscount, result.get("discount"));
    }

    @Test
    public void calcFunctionShouldNotReturnADiscountGreaterThanPriceIfIsExpress() throws PrintShopException {
        PrintShop printShopImpl = new PrintShopImpl();
        Double expectedPrice = 250.0;
        Double expectedDiscount = 250.0;
        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 5)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        result = printShopImpl.calcOrderDetails(printShopImpl.size(), 100, 200, true);
        assertEquals(expectedPrice, result.get("price"));
        assertEquals(expectedDiscount, result.get("discount"));
    }

    @Test
    public void calcFunctionShouldNotReturnADiscountGreaterThanPriceIfIsNotExpress() throws PrintShopException {
        PrintShop printShopImpl = new PrintShopImpl();
        Double expectedPrice = 200.0;
        Double expectedDiscount = 200.0;
        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 5)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        result = printShopImpl.calcOrderDetails(printShopImpl.size(), 100, 200, false);
        assertEquals(expectedPrice, result.get("price"));
        assertEquals(expectedDiscount, result.get("discount"));
    }

    @Test
    public void calcFunctionShouldCalculatePriceWithExpressOptionCorrectly() throws PrintShopException {
        PrintShop printShopImpl = new PrintShopImpl();
        Double expectedPrice = 1000.0;
        expectedPrice = expectedPrice * 1.25;
        Double expectedDiscount = 0.0;
        Double expectedDiscountWithDelay = 187.50;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 6)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        result = printShopImpl.calcOrderDetails(printShopImpl.size(), 1, 1000, true);
        assertEquals(expectedPrice, result.get("price"));
        assertEquals(expectedDiscount, result.get("discount"));

        result = printShopImpl.calcOrderDetails(printShopImpl.size(), 3, 1000, true);
        assertEquals(expectedDiscountWithDelay, result.get("discount"));
    }

    @Test
    public void calcFunctionShouldCalculateDiscountCorrectly() throws PrintShopException {
        PrintShop printShopImpl = new PrintShopImpl();
        Double expectedPrice = 1000.0;
        Double expectedDiscountWithoutDelay = 0.0;
        Double expectedDiscountWithDelay = 100.0;

        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 4)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        result = printShopImpl.calcOrderDetails(printShopImpl.size(), 1, 1000, false);

        assertEquals(expectedPrice, result.get("price"));
        assertEquals(expectedDiscountWithoutDelay, result.get("discount"));

        result = printShopImpl.calcOrderDetails(4, 3, 1000, false);
        assertEquals(expectedDiscountWithDelay, result.get("discount"));
    }

    @Test(expected = PrintShopException.class)
    public void testPopOnEmptyStack() throws Exception {

        PrintShop printShopImpl = new PrintShopImpl();
        printShopImpl.pop();

    }

    @Test(expected = PrintShopException.class)
    public void testPushOnFullStack() throws Exception {

        PrintShop printShopImpl = new PrintShopImpl();
        int ordernumber = 0;
        String modell = "";
        Order order = new Order(1,"Modell1");
        while(printShopImpl.size() != 100)
        {
            ordernumber++;
            modell = "Modell"+ ordernumber;
            printShopImpl.push(new Order(ordernumber, modell));
        }

        modell = "Modell"+ordernumber++;
        printShopImpl.push(new Order(ordernumber++, modell));

    }

    @Test
    public void testPushOnEmptyStack() throws Exception {

        PrintShop printShopImpl = new PrintShopImpl();
        printShopImpl.push(new Order(1, "Modell1"));

    }
}