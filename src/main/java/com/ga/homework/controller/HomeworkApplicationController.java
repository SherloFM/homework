package com.ga.homework.controller;


import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "app.profile")
@RestController
@RequestMapping("/api")
public class HomeworkApplicationController {

    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    private String name;
    private String introduction;
    private String theme;
    private List<String> facts;

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
        this.items = items;
    }

    private Map<String,String> items;
    private String favoriteEntity;
    private String currentlyLearning;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }

    public String getFavoriteEntity() {
        return favoriteEntity;
    }

    public void setFavoriteEntity(String favoriteEntity) {
        this.favoriteEntity = favoriteEntity;
    }

    public String getCurrentlyLearning() {
        return currentlyLearning;
    }

    public void setCurrentlyLearning(String currentlyLearning) {
        this.currentlyLearning = currentlyLearning;
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String Greeting(){
        String text = getAppName()+" "+getName()+" "+ getTheme()+" " + getIntroduction();
        return text;
    }

    @GetMapping("/items")
    public String items(){
        return items.toString();
    }

    @GetMapping("/choose-one")
    public String choose(
            @RequestParam(value = "chosen")int chosen
    ){
        if(chosen<=0 && chosen<items.size()){
            return items.get(chosen);
        }
        return "enter valid value";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "text",defaultValue = "")String text
    ){
        if (!text.isEmpty()){
            List<String> match = facts.stream().filter(fact -> facts.contains(text)).collect(Collectors.toList());
            if(items.containsKey(text)){
                return text;
            }

            if(!match.isEmpty()){

            return match.toString();
            }else {
            return "Invalid";

            }
        }
        return "Enter text to search";
    }

    @GetMapping("/filter")
    public String filter(
            @RequestParam(value = "text",defaultValue = "")String text
    ){
        if(!text.isEmpty()){

        List<String> filtered = items.entrySet().stream().
                filter(entry -> entry.getValue().equals(text))
                .map(entry ->entry.getKey()).toList();
        return filtered.toString();
        }else {
            return "add text";
        }
    }

    @GetMapping("/add")
    public String addnew(
            @RequestParam(value = "category",defaultValue = "")String category,
            @RequestParam(value = "item",defaultValue = "")String item
    ){
        if (!category.isEmpty()){
            if (!item.isEmpty()){
                items.put(category,item);
                return items.toString();
            }
            return "add item";
        }
        return "add category";
    }

    @GetMapping("/update")
    public String update(
            @RequestParam(value = "category", defaultValue = "") String category,
            @RequestParam(value = "item", defaultValue = "") String item
    ){
        if (!category.isEmpty() && items.containsKey(category)){
            if (!item.isEmpty()){
                items.replace(category, item);
                return items.toString();
            }
            return "incorrect item";
        }
        return "incorrect category";
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam(value = "category", defaultValue = "") String category,
            @RequestParam(value = "item", defaultValue = "") String item
    ){
        if (!category.isEmpty() && items.containsKey(category)){
            if (!item.isEmpty() && items.containsValue(item)){
                items.remove(category, item);
                return items.toString();
            }
            return "incorrect item";
        }
        return "incorrect category";
    }

    @GetMapping("/stats")
    public String stats(){
        return "number of Items ="+items.size() + " Number of Facts =" + facts.size();
    }


    @GetMapping("/fav")
    public String fav(){
        return favoriteEntity;
    }

}
