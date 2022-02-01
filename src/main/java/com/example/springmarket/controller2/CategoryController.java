package com.example.springmarket.controller2;

import com.example.springmarket.model.Category;
import com.example.springmarket.model.user.User;
import com.example.springmarket.service.AuthSessionIdService;
import com.example.springmarket.service.CategoryService;
import com.example.springmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    AuthSessionIdService authSessionIdService;

    @PostMapping("/create")
    public String createCategory(@RequestBody Category category){
        categoryService.save(category);
        return "a new category created";
    }

    @GetMapping("/list")
    public String getList(Model model, HttpSession session) throws Exception {
        //мы полчаем сессию
        String sessionId = session.getId();
        //получаем имя юзера
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(currentUserName);
        //получаем обьект юзеря по имени ()
        User user = userService.findByEmail(currentUserName);
        //Сохраняем entity SessionId если это в первый раз(authSessionRep.save( User user, String Session ))
        if(!authSessionIdService.findBySessionId(sessionId))
             authSessionIdService.save(user,sessionId);

        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        return "index";

    }

    @PostMapping("/update/{categoryId}")
    public String updateCategory(@RequestBody Category category, @PathVariable("categoryId") int categoryId){
        if(!categoryService.findById(categoryId)){
            return "Category does not exist";
        }
        categoryService.updateById(categoryId, category);
        return "category"+ Integer.toString(categoryId)+" was updated";
    }
}
