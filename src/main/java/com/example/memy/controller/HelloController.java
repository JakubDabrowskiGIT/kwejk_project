package com.example.memy.controller;


import com.example.memy.DAO.CategoryDao;
import com.example.memy.DAO.GifDao;
import com.example.memy.model.category;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
public class HelloController {

    GifDao gifDao = new GifDao();
    CategoryDao categoryDao = new CategoryDao();

    @GetMapping("/")
    public String hello(ModelMap modelMap){
        modelMap.addAttribute("gifs", gifDao.showAll() );
        return "home";
    }
    @GetMapping("/gifs/search")
    public String search(@RequestParam String q, ModelMap modelMap){
    modelMap.addAttribute("gifs",gifDao.findByName(q));
    if(gifDao.findByName(q).size() ==0) {
        modelMap.addAttribute("gifs", gifDao.findByNameCat(q));
    }
        return "home";
    }
    @GetMapping("/categories")
    public String hello3( ModelMap modelMap){
        modelMap.addAttribute("categories", categoryDao.showAllCategory());
        return "categories";
    }
    @GetMapping("/articles")
    public String articles(){
        return "articles";
    }
    @GetMapping("/favorites")
    public String favorites( ModelMap modelMap){
        modelMap.addAttribute("gifs", gifDao.isLike() );
        return "isLike";
    }
    @GetMapping("/gif/{name}")
    public String displayMem(@PathVariable String name, ModelMap modelMap) {
        modelMap.addAttribute("gif", gifDao.findOne(name).get(0));
        return "gif-details";
    }
    @GetMapping("/category/1")
    public String categoryTopRated(ModelMap modelMap) {
        modelMap.addAttribute("category", categoryDao.showAllCategory().get(0));
        modelMap.addAttribute("gifs", gifDao.findTopRated());
        return "category";
    }
    @GetMapping("/category/2")
    public String categoryMostPopular(ModelMap modelMap) {
        modelMap.addAttribute("category", categoryDao.showAllCategory().get(1));
        modelMap.addAttribute("gifs", gifDao.findAnimals());
        return "category";
    }
    @GetMapping("/category/3")
    public String categoryNew(ModelMap modelMap) {
        modelMap.addAttribute("category", categoryDao.showAllCategory().get(2));
        modelMap.addAttribute("gifs", gifDao.findNew());
        return "category";
    }

@GetMapping("/category")
    public String searchByCategory(ModelMap modelMap){
return "category";
    }


    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping("/facebook")
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }



}
