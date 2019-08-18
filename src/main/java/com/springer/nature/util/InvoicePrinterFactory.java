package com.springer.nature.util;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.exception.CafeServiceException;
import com.springer.nature.service.InvoicePrinter;
import com.springer.nature.service.impl.TextPrinter;

public class InvoicePrinterFactory {
    public static InvoicePrinter getInvoicePrinter(String type){
        switch (type)
        {
            case CafeConstant
                    .PRINT_TEXT:
                    return new TextPrinter();
             default:
                 throw new CafeServiceException("Wrong Printer");
        }
    }

}
