package com.springer.nature.printer;

import com.springer.nature.models.Invoice;

public interface InvoicePrinter {
    void prettyPrint(Invoice invoice);
}
