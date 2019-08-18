package com.springer.nature.constant;

public enum DiscountRange {
    TEN(10),TWENTY(20), TWENTY_FIVE(25);
    private  int discountRange;

    DiscountRange(int discountRange) {
        this.discountRange=discountRange;
    }

    public int getDiscountRange() {
        return discountRange;
    }
}
