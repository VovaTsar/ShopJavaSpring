package com.example.shop.controller;

import com.example.shop.entity.Orders;
import com.example.shop.entity.User;
import com.example.shop.repository.OrderRepository;
import com.example.shop.service.ShoppingCartService;
import com.example.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public UserController(UserService userService, OrderRepository orderRepository, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/user")
    public String userPanel(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        List<Orders> orders = shoppingCartService.findOrdersByUserId(user.getId());
        shoppingCartService.totalPrice(user.getId());
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("orders", orders);
        } else {
            return "error/404";
        }

        return "user";
    }
}
