package com.verissimo.servicelimit.controller;

import com.verissimo.servicelimit.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

  @GetMapping("/current")
  public String orderForm(Model model) {
    model.addAttribute("order", new Order());
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("order", order);
      return "orderForm";
    }

    log.info("Order submitted: " + order);
    return "redirect:/";
  }

}
