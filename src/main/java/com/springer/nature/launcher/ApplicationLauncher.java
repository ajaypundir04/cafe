package com.springer.nature.launcher;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.models.Invoice;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;
import com.springer.nature.printer.InvoicePrinter;
import com.springer.nature.printer.InvoicePrinterStrategy;
import com.springer.nature.printer.TextPrinter;
import com.springer.nature.service.DiscountService;
import com.springer.nature.service.ProductService;
import com.springer.nature.service.impl.CafeServiceImpl;
import com.springer.nature.service.impl.DiscountServiceImpl;
import com.springer.nature.service.impl.MenuService;
import com.springer.nature.service.impl.ProductServiceImpl;
import com.springer.nature.util.FileReader;

import java.io.IOException;
import java.util.List;

/**
 * @author Ajay Singh Pundir
 * Entry point of the application
 */
public class ApplicationLauncher {

    private static CafeServiceImpl cafeService;

    public static void main(String[] args) throws IOException {
        cafeService = initialize();
        List<Order> orders = FileReader.loadOrder("orders.json", CafeConstant.JSON_EXTENSION);
        Invoice invoice = cafeService.processOrder(orders);
        InvoicePrinter printer = new TextPrinter();
        printInvoice(invoice, printer);
    }

    /**
     *
     * @return {@link com.springer.nature.service.CafeService} instance
     * @throws IOException
     * This methid is used to inject the depenecy required by the cafeService
     */
    private static CafeServiceImpl initialize() throws IOException {
        Product[] products = FileReader.loadProduct("products.json", CafeConstant.JSON_EXTENSION);
        MenuService menuService = new MenuService();
        for (Product p : products) {
            //menuService.addOrUpdateMenu(p);
            int varietyCount = p.getVariations().size();
            p.getVariations().forEach(variations -> {
                variations.setQuantity(CafeConstant.DEFAULT_QUANTITY / varietyCount);
            });
            menuService.addOrUpdateMenu(p);
        }
        ProductService productService = new ProductServiceImpl();
        DiscountService discountService = new DiscountServiceImpl();
        CafeServiceImpl cafeService = CafeServiceImpl.getInstance();
        cafeService.setMenuService(menuService);
        cafeService.setDiscountService(discountService);
        cafeService.setProductService(productService);
        return cafeService;
    }

    private static void printInvoice(Invoice invoice, InvoicePrinter printer) {
        InvoicePrinterStrategy invoicePrinterStrategy = new InvoicePrinterStrategy(printer);
        invoicePrinterStrategy.prettyPrint(invoice);
    }
}
