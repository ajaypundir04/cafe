package com.springer.nature.printer;

import com.springer.nature.models.Invoice;

/**
 * @author Ajay Singh Pundir
 * It is used to print the invoice
 */
public interface InvoicePrinter {

    void prettyPrint(Invoice invoice);
}
