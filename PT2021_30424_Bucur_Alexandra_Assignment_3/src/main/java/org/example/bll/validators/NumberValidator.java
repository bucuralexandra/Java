package org.example.bll.validators;

import org.example.model.Product;


/**
 * @author Alexandra
 * @class NumberValidator
 * Class that validates if a product has negative stock products(which is not okay)
 * implements the interface Validator
 * @since 18/04/2021
 */
public class NumberValidator implements Validator<Product> {
    /**
     * @param product product that is validated
     * @return an int, if the stockNumber is negative, the method will return a -1 output(incorrect object)
     * and 0 otherwise
     */
    @Override
    public int validate(Product product) {
        if (product.getNrProductsInStock() < 0) {
            return -1;
        }
        return 0;
    }
}
