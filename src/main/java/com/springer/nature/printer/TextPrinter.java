package com.springer.nature.printer;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.models.Invoice;
import com.springer.nature.models.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Ajay Singh Pundir
 * This is the implementation for @{@link InvoicePrinter}
 */
public class TextPrinter implements InvoicePrinter {

    /**
     * @param invoice It is used to print the invoice in the console
     */
    @Override
    public void prettyPrint(Invoice invoice) {
        System.out.println(String.join("",
                String.format("%-20s", "Item Name"), String.format("%-20s", "Count"), String.format("%-20s", "Price (INR)")));
        invoice.allOrders().forEach(o -> prettyPrintOrder(o, invoice.getPrice(o)));
        System.out.println(String.join("", String.format("%-20s", "Total"),
                String.format("%-20s", ""),
                String.format("%-20s", String.valueOf(Double.valueOf(invoice.getTotalAmount()).intValue()))
        ));

        System.out.println(String.join("", String.format("%-20s", String.join("",
                CafeConstant.DISCOUNT, "-", String.valueOf(Double.valueOf(invoice.getDiscountRange().getDiscountRange()).intValue()), " %")
                ),
                String.format("%-20s", ""),
                String.format("%-20s", String.valueOf(roundOff(invoice.getDiscount(), 2)))
        ));
        System.out.println(String.join("", String.format("%-20s", "Final Amount"),
                String.format("%-20s", ""),
                String.format("%-20s", String.valueOf(roundOff(invoice.getFinalAmount(), 2)).concat(" INR"))
        ));
    }

    private void prettyPrintOrder(Order order, double price) {
        System.out.println(String.join("",
                String.format("%-20s", order.getName()),
                String.format("%-20d", order.getQuantity()),
                String.format("%-20s", String.valueOf(Double.valueOf(price).intValue()))));
    }

    private double roundOff(double value, int decimalPlaces) {
        return new BigDecimal(value).setScale(decimalPlaces, RoundingMode.UP).doubleValue();
    }


}
