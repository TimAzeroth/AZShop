package com.azeroth.project.controller;

import com.azeroth.project.domain.CategoryDomain;
import com.azeroth.project.domain.ProductDomain;
import com.azeroth.project.domain.ProductValidator;
import com.azeroth.project.service.CategoryService;
import com.azeroth.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public void add(Model model) {
        List<CategoryDomain> mainCategories = categoryService.findAllMain();
        List<CategoryDomain> subCategories = categoryService.findAllSub();
        List<CategoryDomain> categories = categoryService.findAll();
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("categories", categories);
    }

    @PostMapping("/add")
    public String addOk(@RequestParam("upfile") MultipartFile file, @Valid ProductDomain productDomain, BindingResult result, RedirectAttributes attr, Model model) {
        if (result.hasErrors()) {

            attr.addFlashAttribute("p_name", productDomain.getP_name());
            attr.addFlashAttribute("detail", productDomain.getDetail());
            attr.addFlashAttribute("price", productDomain.getPrice());
            attr.addFlashAttribute("stock", productDomain.getStock());
            attr.addFlashAttribute("p_rank", productDomain.getP_rank());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList) {
                attr.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/product/add";

        }
        int sqlResult = productService.addProduct(productDomain, file);
        model.addAttribute("sqlResult", sqlResult);
        return "product/addOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator());
    }


}
