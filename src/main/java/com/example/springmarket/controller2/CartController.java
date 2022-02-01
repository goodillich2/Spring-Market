package com.example.springmarket.controller2;


import com.example.springmarket.Dto.cart.AddToCartDto;
import com.example.springmarket.Dto.cart.CartDto;
import com.example.springmarket.model.user.User;
import com.example.springmarket.response.ApiResponse;
import com.example.springmarket.service.AuthSessionIdService;
import com.example.springmarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    AuthSessionIdService authSessionIdService;



    // post cart api
    @PostMapping("/add")
    public String  addToCart(@RequestParam("quantity") Integer quantity, @RequestParam("productId") Integer productId,  HttpSession session) throws Exception {
        AddToCartDto addToCartDto = new AddToCartDto(productId,quantity);
        System.out.println(addToCartDto);


        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);


        cartService.addToCart(addToCartDto, user);

        return "redirect:/category/list";
    }


    // get all cart items for a user
    @GetMapping("/")
    public String getCartItems(HttpSession session, Model model) throws Exception {
        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);

        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        model.addAttribute("cartDto", cartDto);
        return "cartItems";
    }

    // delete a cart item for a user

    @PostMapping("/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") Integer itemId, HttpSession session) throws Exception {

        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);


        cartService.deleteCartItem(itemId, user);

        return "redirect:/cart/";

    }
}

