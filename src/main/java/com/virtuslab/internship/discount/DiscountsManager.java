package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

public class DiscountsManager {

    public Receipt apply(Receipt receipt) {
        GrainProductsDiscount discountAtLeast3GrainsProducts = new GrainProductsDiscount();
        TenPercentDiscount discountPriceOver50 = new TenPercentDiscount();

        var receiptAfterDiscount = discountAtLeast3GrainsProducts.apply(receipt);
        receiptAfterDiscount = discountPriceOver50.apply(receiptAfterDiscount);

        return receiptAfterDiscount;
    }
}
