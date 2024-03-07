package com.interview.project.service;

import com.interview.project.dto.CustomerLoginRequest;
import com.interview.project.dto.CustomerLoginResponse;
import com.interview.project.dto.PlaceOrderRequest;
import com.interview.project.dto.PlaceOrderResponse;
import com.interview.project.entity.CustomerOrder;
import com.interview.project.exception.ErrorInfo;
import com.interview.project.exception.SystemRuntimeException;
import com.interview.project.repository.ICustomerOrderRepository;
import com.interview.project.repository.ICustomerRepository;
import com.interview.project.repository.IMenuItemRepository;
import com.interview.project.repository.IShopRepository;
import com.interview.project.security.JWTProvider;
import com.interview.project.security.ProfileLocal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {

    private final ICustomerRepository customerRepository;
    private final IShopRepository shopRepository;
    private final IMenuItemRepository menuItemRepository;
    private final ICustomerOrderRepository customerOrderRepository;
    private final JWTProvider jwtProvider;

    @Transactional(readOnly = true)
    public CustomerLoginResponse login(CustomerLoginRequest request) {
        var customer = customerRepository.findByUserName(request.getUserName());
        if (customer == null) {
            throw new SystemRuntimeException(HttpStatus.UNAUTHORIZED, ErrorInfo.INVALID_USER_NAME_PASSWORD, "Invalid User Name");
        }

        if (!customer.getPassword().equals(request.getPassword())) {
            throw new SystemRuntimeException(HttpStatus.UNAUTHORIZED, ErrorInfo.INVALID_USER_NAME_PASSWORD, "Invalid Password");
        }

        return CustomerLoginResponse.builder().accessToken(jwtProvider.generate(String.valueOf(customer.getId()))).build();
    }

    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        //Validate Request
        var orderItem = menuItemRepository.findById(request.getOrderItemId());
        if (!orderItem.isPresent()) {
            throw new SystemRuntimeException(HttpStatus.BAD_REQUEST, ErrorInfo.INVALID_MENU_ITEM, "Invalid Menu Item");
        }

        var shop = orderItem.get().getShop();
        if (shop.getMaximumSizeOfQueue() <= shop.getCurrentNumberOfQueue()) {
            throw new SystemRuntimeException(HttpStatus.BAD_REQUEST, ErrorInfo.SHOP_QUEUE_IS_FULL, "Queue is full. Please wait!");
        }

        var customer = customerRepository.findById(Long.valueOf(ProfileLocal.getUserId()));
        if (!customer.isPresent()) {
            throw new SystemRuntimeException(HttpStatus.BAD_REQUEST, ErrorInfo.INVALID_CUSTOMER, "Invalid Customer");
        }

        //Update shop queue
        shop.setCurrentNumberOfQueue(shop.getCurrentNumberOfQueue() + 1);
        shop = shopRepository.save(shop);

        //Place order
        var order = customerOrderRepository.save(CustomerOrder.builder()
                .customer(customer.get())
                .menuItem(orderItem.get())
                .queueNumber(shop.getCurrentNumberOfQueue())
                .orderStatus("CREATED")
                .build());

        return PlaceOrderResponse.builder().orderId(order.getId()).queueNumber(order.getQueueNumber()).build();
    }
}
