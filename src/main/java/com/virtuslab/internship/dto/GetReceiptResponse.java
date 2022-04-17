package com.virtuslab.internship.dto;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.cglib.core.internal.Function;

import java.util.List;

public class GetReceiptResponse {

    private Receipt receipt;

    public GetReceiptResponse(List<Product> productsInBasket) {
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();
        Basket basket = new Basket();
        productsInBasket.forEach(basket::addProduct);
        receipt = receiptGenerator.generate(basket);
    }

    public static Function<List<Product>, GetReceiptResponse> entityToDtoMapper() {
        return GetReceiptResponse::new;
    }

    public Receipt getReceipt() {
        return receipt;
    }
}
