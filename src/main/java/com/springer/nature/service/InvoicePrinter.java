package com.springer.nature.service;

import com.springer.nature.models.Invoice;

public interface InvoicePrinter {
    void prettyPrint(Invoice invoice);
}
