package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import db.CustomerDB;
import db.DataAccessException;
import db.OrderDB;
import db.ProductDB;
import model.OrderLineItem;

class OrderControllerTest {

    private static final int PRODUCT_NO = 1001; 
    private static final int STOCK = 5;

    private OrderController orderController;

    @BeforeEach
    void setUp() throws DataAccessException {
        orderController = new OrderController(new CustomerController(new CustomerDB()),
        		new ProductController(new ProductDB()),new OrderDB());
    }

    private void startOrderWithProduct() throws DataAccessException {
        orderController.createOrder();
        orderController.enterProductNumber(PRODUCT_NO);
    }

    // T1 
    @Test
    void testNegativeQty_ThrowsException() throws DataAccessException {
        startOrderWithProduct();
        assertThrows(IllegalArgumentException.class, () -> orderController.enterQuantity(-3));
    }

    // T2 
    @Test
    void testValidQty_AddsToOrder() throws DataAccessException {
        startOrderWithProduct();
        OrderLineItem item = orderController.enterQuantity(4);
        assertNotNull(item);
        assertEquals(4, item.getQuantity());
        assertEquals(1, orderController.getCurrentOrder().getLines().size());
    }

    // T3 
    @Test
    void testQtyExceedsStock_ThrowsException() throws DataAccessException {
        startOrderWithProduct();
        assertThrows(IllegalArgumentException.class, () -> orderController.enterQuantity(10));
    }

    // T4 
    @Test
    void testQtyEqualsStock_AddsToOrder() throws DataAccessException {
        startOrderWithProduct();
        OrderLineItem item = orderController.enterQuantity(STOCK);
        assertNotNull(item);
        assertEquals(STOCK, item.getQuantity());
        assertEquals(1, orderController.getCurrentOrder().getLines().size());
    }
}
