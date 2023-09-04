package kz.shop.auto_parts.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import kz.shop.auto_parts.entities.OrderEntity;
import kz.shop.auto_parts.entities.dto.OrderDto;
import kz.shop.auto_parts.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<OrderEntity>> getAll() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable @Valid @Positive Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.OK);
    }

    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<Void> deleteById(@PathVariable("orderId") @Valid @Positive Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateStatus(@PathVariable("orderId") @Valid @Positive Long orderId,
                                               @RequestParam @Valid @NotBlank String status) {
        String newStatus = orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(newStatus, HttpStatus.OK);
    }
}
