package com.verissimo.servicelimit.controller;

import com.verissimo.servicelimit.entity.Ingredient;
import com.verissimo.servicelimit.entity.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.verissimo.servicelimit.entity.Ingredient.Type.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = List.of(
        new Ingredient("FLTO", "Flour Tortilla", WRAP),
        new Ingredient("COTO", "Corn Tortilla", WRAP),
        new Ingredient("GRBF", "Ground Beef", PROTEIN),
        new Ingredient("CARN", "Carnitas", PROTEIN),
        new Ingredient("TMTO", "Diced Tomatoes", VEGGIES),
        new Ingredient("LETC", "Lettuce", VEGGIES),
        new Ingredient("CHED", "Cheddar", CHEESE),
        new Ingredient("JACK", "Monterrey Jack", CHEESE),
        new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", SAUCE)
    );

    Ingredient.Type[] types = values();
    for (Ingredient.Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    model.addAttribute("design", new Taco());

    return "design";
  }

  @PostMapping
  public String process(@Valid Taco taco, Errors errors) {
    if (errors.hasErrors()) {
      System.out.printf("here");
      System.out.printf(errors.toString());
      return "design";
    }
    // Save the taco design...
    // We'll do this in chapter 3
    log.info("Processing design: " + taco);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
    return ingredients.stream().filter(el -> el.getType() == type).collect(Collectors.toList());
  }

}
