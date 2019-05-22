package com.example.shop.service.impl;

import com.example.shop.entity.Orders;
import com.example.shop.entity.User;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.UserService;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final String FINAL_STATUS = "ORDER IS CONSIDERED";
    private static final String ADMIN_STATUS = "Waiting for confirmation from the seller";
    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        System.out.println("token" + token);
        if (token.isAuthenticated()) {
            //контекст де збериг користувачы аунтетификовани
            SecurityContextHolder.getContext().setAuthentication(token);
            logger.debug(String.format("User %s logged in successfully!", username));
        } else {
            logger.error(String.format("Error with %s authentication!", username));
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }


    @Override
    public void editStatus( Orders orders,int userId) {
          orders.setStatus(FINAL_STATUS);
          orderRepository.save(orders);

        }

    @Override
    public List<Orders> listBuyers( ) {
        List<Orders> orders =orderRepository.findByStatus(ADMIN_STATUS);
        List<Orders> orders2 = orderRepository.findByStatus(FINAL_STATUS);
        int count=0;

        for (Orders ord:orders2) {
            orders.add(orders2.get(count));
            count++;
        }
        return  orders;
    }


}
