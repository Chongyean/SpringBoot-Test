package com.yean.demo.service;

import com.yean.demo.dto.order.OrderDto;
import com.yean.demo.entity.Order;
import com.yean.demo.mapper.OrderMapper;
import com.yean.demo.model.BaseResponseModel;
import com.yean.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<BaseResponseModel> createOrder(OrderDto payload) {
        Order order = mapper.toEntity(payload);

        orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("success", "successfully placed order"));
    }
}