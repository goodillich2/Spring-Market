package com.example.springmarket.controller;


import com.example.springmarket.Dto.cart.AddToCartDto;
import com.example.springmarket.Dto.cart.CartDto;
import com.example.springmarket.model.user.User;
import com.example.springmarket.response.ApiResponse;
import com.example.springmarket.service.AuthSessionIdService;
import com.example.springmarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    AuthSessionIdService authSessionIdService;



    // post cart api
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, HttpSession session) throws Exception {

        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);


        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }


    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(HttpSession session) throws Exception {
        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);

        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete a cart item for a user

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId, HttpSession session) throws Exception {

        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);



        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }

}

