package com.virtuslab.internship.controller;

import com.virtuslab.internship.dto.GetReceiptResponse;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ProductDb productDb;

    @GetMapping
    public @ResponseBody String hello() {
        return "Hello Virtuslab!";
    }

    @PostMapping
    public ResponseEntity<GetReceiptResponse> getReceipt(@RequestBody List<String> productsInBasketString) {
        List<Product> productsInBasket = new ArrayList<>();
        productsInBasketString.forEach(product -> {
            var foundProduct = productDb.getProduct(product);
            if(foundProduct != null) { productsInBasket.add(foundProduct); }
        });
        return ResponseEntity.ok(GetReceiptResponse.entityToDtoMapper().apply(productsInBasket));
    }
}
