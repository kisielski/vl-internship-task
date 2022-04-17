package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrainProductsDiscountTest {

    @Test
    void shouldApplyOnlyGrainDiscount() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 4));

        var receipt = new Receipt(receiptEntries);
        var discounts = new DiscountsManager();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(4)).multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discounts.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApplyBothDiscounts() {
        // Given
        var productDb = new ProductDb();
        var steak = productDb.getProduct("Steak");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(steak, 1));
        receiptEntries.add(new ReceiptEntry(cereals, 3));

        var receipt = new Receipt(receiptEntries);
        var discounts = new DiscountsManager();
        var expectedTotalPrice = cereals.price().multiply(BigDecimal.valueOf(3)).add(steak.price()).multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscount = discounts.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }
}
