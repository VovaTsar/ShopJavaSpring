package com.example.shop.controller;

import com.example.shop.entity.Orders;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.service.ProductService;
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

@Controller
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, ProductService productService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String cart(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Orders orders =shoppingCartService.getOrders(user);
        orders.setPriceOrd(shoppingCartService.totalPrice(user.getId()));
        model.addAttribute("products", orders.getProducts());
        model.addAttribute("totalPrice", orders.getPriceOrd());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") int id, Principal principal) {
        Product product = productService.findById(id);
        User user = userService.findByUsername(principal.getName());
        if (product != null) {
            shoppingCartService.addProduct(product, user);
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") int id, Principal principal) {
        Product product = productService.findById(id);
        User user = userService.findByUsername(principal.getName());
        if (product != null) {
            shoppingCartService.removeProduct(product, user.getId());
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear/")
    public String clearProductsInCart( Principal principal) {
        User user = userService.findByUsername(principal.getName());
        shoppingCartService.clearProducts(user.getId());

        return "redirect:/cart";
    }

    @GetMapping("/cart/final")
    public String cartCheckout() {


        return "/final";
    }
}
