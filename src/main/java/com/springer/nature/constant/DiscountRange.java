package com.springer.nature.constant;

public enum DiscountRange {

    ZERO(0.0), TEN(10.0), TWENTY(20.0), TWENTY_FIVE(25.0);
    private double discountRange;

    DiscountRange(double discountRange) {
        this.discountRange = discountRange;
    }

    public double getDiscountRange() {
        return discountRange;
    }
}
