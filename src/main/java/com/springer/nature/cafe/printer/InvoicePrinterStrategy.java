package com.springer.nature.cafe.printer;

import com.springer.nature.cafe.models.Invoice;

/**
 * @author Ajay Singh Pundir
 * This class acts as an interface for concerete object which will print the invoice
 */
public class InvoicePrinterStrategy implements InvoicePrinter {

    private InvoicePrinter invoicePrinter;

    public InvoicePrinterStrategy(InvoicePrinter invoicePrinter) {
        this.invoicePrinter = invoicePrinter;
    }

    @Override
    public void prettyPrint(Invoice invoice) {
        invoicePrinter.prettyPrint(invoice);
    }
}
