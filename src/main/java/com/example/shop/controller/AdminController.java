package com.example.shop.controller;

import com.example.shop.entity.Orders;
import com.example.shop.entity.User;
import com.example.shop.repository.OrderRepository;
import com.example.shop.service.ShoppingCartService;
import com.example.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AdminController(UserService userService,ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        List<Orders> orders = userService.listBuyers();
        model.addAttribute("orders", orders);

        return "admin";
    }

    @GetMapping("/admin/status/{id}")
    public String addStatus(@PathVariable("id") int id, Principal principal) {
       Orders order =shoppingCartService.findById(id);
        User user = userService.findByUsername(principal.getName());
        if (order != null) {
            userService.editStatus(order, user.getId());
            logger.debug(String.format("Status order with id: %s changed .", id));
        }
        return "redirect:/admin";
    }

}
