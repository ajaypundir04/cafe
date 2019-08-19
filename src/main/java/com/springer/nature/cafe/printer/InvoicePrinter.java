package com.springer.nature.cafe.printer;

import com.springer.nature.cafe.models.Invoice;

/**
 * @author Ajay Singh Pundir
 * It is used to print the invoice
 */
public interface InvoicePrinter {

    void prettyPrint(Invoice invoice);
}
