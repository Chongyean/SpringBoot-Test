package com.yean.demo.controller;

import com.yean.demo.dto.order.OrderDto;
import com.yean.demo.model.BaseResponseModel;
import com.yean.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<BaseResponseModel> placeOrder(@Valid @RequestBody OrderDto payload) {
        return orderService.createOrder(payload);
    }
}