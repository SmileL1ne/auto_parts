package kz.shop.auto_parts.services;

import kz.shop.auto_parts.entities.OrderEntity;
import kz.shop.auto_parts.entities.ProductEntity;
import kz.shop.auto_parts.entities.dto.OrderDto;
import kz.shop.auto_parts.exceptions.OrderNotFoundException;
import kz.shop.auto_parts.exceptions.ProductNotFoundException;
import kz.shop.auto_parts.repositories.OrderRepository;
import kz.shop.auto_parts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            OrderEntity order = optionalOrder.get();
            return new OrderDto(order.getName(), order.getAmount(),
                    order.getTotalPrice());
        } else {
            throw new OrderNotFoundException("Order " + id + " not found", "404");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity order = new OrderEntity();
        order.setName(orderDto.getName());
        order.setAmount(orderDto.getAmount());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setShippingAddress("My Address");
        order.setOrderStatus("New");
        OrderEntity createdOrder = orderRepository.save(order);

        OrderDto newOrderDto = new OrderDto();
        newOrderDto.setName(createdOrder.getName());
        newOrderDto.setAmount(createdOrder.getAmount());
        newOrderDto.setTotalPrice(createdOrder.getTotalPrice());
        return newOrderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOrderById(Long id) {
        orderRepository.deleteByOrderId(id).orElseThrow(() ->
                new OrderNotFoundException("Couldn't find order with id - " + id, "404"));
    }

    @Override
    public Double calculateOrderTotal(List<OrderEntity> orders) {
        double total = 0;
        for (OrderEntity order: orders) {
            ProductEntity product = productRepository.getProductEntityByProductId(
                    order.getProduct().getProductId())
                    .orElseThrow(()->new ProductNotFoundException(
                            "Couldn't find product with id - " + order.getProduct().getProductId(), "404"));
            total += product.getPrice() * order.getAmount();
        }
        return total;
    }

    @Override
    public Double calculateTotalAmountSpentByUser(Long userId) {
        List<OrderEntity> orders = orderRepository.findAllByUserUserId(userId);
        double total = 0;
        for (OrderEntity order: orders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    @Override
    public String showOrderStatus(Long orderId) {
        OrderEntity order = orderRepository.findByOrderId(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Coundn't find the order with id - " + orderId, "404"));
        return order.getOrderStatus();
    }

    @Override
    public String updateOrderStatus(Long orderId, String status) {
        OrderEntity order = orderRepository.findByOrderId(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Coundn't find the order with id - " + orderId, "404"));
        order.setOrderStatus(status);
        return status;
    }
}
