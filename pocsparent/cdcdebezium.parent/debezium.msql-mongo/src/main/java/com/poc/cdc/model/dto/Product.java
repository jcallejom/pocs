package com.poc.cdc.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
  private String id;
  private String name;
  private String description;
  private double weight;
}