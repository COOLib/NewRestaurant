package ua.goit.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.goit.service.IngredientService;
import ua.goit.service.StorageService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/restaurant")
public class HStorageController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private StorageService storageService;

    private static HttpHeaders responseHeaders = new HttpHeaders();


    @RequestMapping(value = "/storage/{ingredientName}/{quantity}", method = RequestMethod.POST, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addIngredientToStorage(@PathVariable("ingredientName") String ingredientName, @PathVariable("quantity") int quantity) {

        storageService.addIngredientToStorage(ingredientName, quantity);
        return new ResponseEntity<>("{\"ingredient\":\"" + ingredientName + "\",\"quantity\":" + quantity + "}", responseHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "/storage/{ingredientName}/{quantity}", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object updateQuantity(@PathVariable("ingredientName") String ingredientName, @PathVariable("quantity") int quantity) {

        storageService.updateQuantity(ingredientName, quantity);
        return new ResponseEntity<>("{\"ingredient\":\"" + ingredientName + "\",\"quantity\":" + quantity + "}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/storage/{name}", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object removeIngredientFromStorage(@PathVariable("name") String name) {

        storageService.removeIngredientFromStorage(name);
        return new ResponseEntity<>("{\"deleted\":\"" + name + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/storage", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllIngredients() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(storageService.getAllIngredients());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/storage/ending", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getEndingIngredients() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(storageService.getEndingIngredients());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/storage/{name}", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getByIngredientName(@PathVariable("name") String name) {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(storageService.getByIngredientName(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}