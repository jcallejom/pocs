package com.poc.cdc.model.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity{

  @Id
  private String _id;
  @Field("firstName")
  private String firstName;
  @Field("description")
  private String lastName;
  @Field("email")
  private String email;
  

}