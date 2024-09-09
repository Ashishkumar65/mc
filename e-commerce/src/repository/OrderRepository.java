package repository;

import exception.EcommerceException;
import model.Order;
import model.ErrorCode;
import utils.ErrorCodeMap;

import java.util.HashMap;

public class OrderRepository {
    HashMap<String, Order> orders;
    public OrderRepository(){
        orders = new HashMap<>();
    }

    public Order createOrder(Order order){
       if(orders.get(order.getOrderId())!=null){
           throw new EcommerceException(
                   ErrorCode.ORDER_CREATION_FAILED, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PRODUCT_ALREADY_CREATED)
           );
       }
         orders.put(order.getOrderId(), order);
       return order;
    }

    public Order getOrder(String orderId){
        return orders.get(orderId);
    }
}
