package com.verissimo.servicelimit.entity;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Order {
  @NotBlank(message = "name is required")
  private String name;
  @NotBlank(message = "street is required")
  private String street;
  @NotBlank(message = "city is required")
  private String city;
  @NotBlank(message = "state is required")
  private String state;
  @NotBlank(message = "zip is required")
  private String zip;
  @CreditCardNumber(message = "credit card number is invalid")
  private String ccNumber;
  @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
  private String ccExpiration;
  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;
}
