package kz.shop.auto_parts.services;

import kz.shop.auto_parts.entities.OrderEntity;
import kz.shop.auto_parts.entities.ProductEntity;
import kz.shop.auto_parts.entities.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderEntity> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto createOrder(OrderDto orderDto);

    void deleteOrderById(Long id);

    Double calculateOrderTotal(List<OrderEntity> orders);

    Double calculateTotalAmountSpentByUser(Long userId);

    String showOrderStatus(Long orderId);

    String updateOrderStatus(Long orderId, String status);
}
