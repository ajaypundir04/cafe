package com.springer.nature.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springer.nature.constant.CafeConstant;
import com.springer.nature.exception.CafeServiceException;
import com.springer.nature.launcher.ApplicationLauncher;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Ajay Singh Pundir
 * Utility class for File handling
 */
public class FileReader {


    private FileReader() {

    }

    /**
     * @param fileName
     * @param type
     * @return Product array
     * It will load the products from product file on the basis of extension
     */
    public static Product[] loadProduct(String fileName, String type) {
        Product[] productList = null;
        switch (type) {
            case CafeConstant.JSON_EXTENSION:
                productList = readJsonProductFile(fileName);
                break;
            default:
                throw new CafeServiceException("Wrong Extension");
        }
        return productList;
    }

    /**
     * @param fileName
     * @param type
     * @return List of Order
     * It will load the list of order from the file on the basis of extension
     */
    public static List<Order> loadOrder(String fileName, String type) {
        List<Order> orderList = null;
        switch (type) {
            case CafeConstant.JSON_EXTENSION:
                orderList = readJsonOrderFile(fileName);
                break;
            default:
                throw new CafeServiceException("Wrong Extension");
        }
        return orderList;
    }

    private static Product[] readJsonProductFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(resourceFileAsUrl(fileName), Product[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Order> readJsonOrderFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(resourceFileAsUrl(fileName), new TypeReference<List<Order>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static URL resourceFileAsUrl(String file) {
        return ApplicationLauncher.class.getClassLoader().getResource(file);
    }

}
