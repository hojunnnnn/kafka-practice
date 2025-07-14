package com.hojunnnnn.kafka_practice.order.ui;

import com.hojunnnnn.kafka_practice.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<String> createOrder(@RequestParam final Long orderId) {
        orderService.createOrder(orderId);
        return ResponseEntity.ok("OK");
    }
}
