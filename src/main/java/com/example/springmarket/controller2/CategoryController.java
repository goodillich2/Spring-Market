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


    @GetMapping("/create")
    public String createCategory(){
        return "AddCategoryForm";
    }

    @PostMapping("/create")
    public String createCategory(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("imageUrl") String imageUrl ){
        Category category = new Category(name, description, imageUrl);
        categoryService.save(category);
        return "redirect:/category/admin/list";
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

    @GetMapping("/update/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") int categoryId, Model model){
        if(!categoryService.findById(categoryId)){
            return "Category does not exist";
        }
        Category category = categoryService.getById(categoryId);
        model.addAttribute("category", category);
        return "UpdateCategoryForm";
    }

    @PostMapping("/update/{categoryId}")
    public String updateCategory(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("imageUrl") String imageUrl, @PathVariable("categoryId") int categoryId){
        Category category = new Category(categoryId, name, description, imageUrl);
        if(!categoryService.findById(categoryId)){
            return "Category does not exist";
        }
        System.out.println(category);
        categoryService.updateById(categoryId, category);
        return "redirect:/category/admin/list";
    }

    @GetMapping("admin/list")
    public String getAdminList(Model model, HttpSession session) throws Exception {
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        return "adminCategories";

    }

    @PostMapping("delete/{id}")
    public String deleteCategory(@PathVariable("id") int id){
        categoryService.deleteById(id);
        return "redirect:/category/admin/list";
    }
}
