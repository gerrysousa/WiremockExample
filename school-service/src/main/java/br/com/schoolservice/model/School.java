package br.com.schoolservice.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
public class School extends AbstractEntity{
  @NotEmpty(message = "Field 'name' is mandatory!")
  private  String name;
  @NotEmpty
  private String abbreviation;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return abbreviation;
  }

  public void setEmail(String email) {
    this.abbreviation = email;
  }
}
