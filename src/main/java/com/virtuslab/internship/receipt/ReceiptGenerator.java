package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountsManager;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        HashMap<Product, Integer> entries = new HashMap<>();
        basket.getProducts().forEach(product -> {
            entries.put(product, entries.get(product) == null ? 1 : entries.get(product) + 1);
        });
        entries.forEach((product, quantity) -> receiptEntries.add(new ReceiptEntry(product, quantity)));

        var discounts = new DiscountsManager();
        return discounts.apply(new Receipt(receiptEntries));
    }
}
