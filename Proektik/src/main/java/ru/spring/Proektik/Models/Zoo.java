package ru.spring.Proektik.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.*;

@Entity
public class Zoo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Котёнок должен быть голым")
    @Size(min = 1, max = 150, message = "Поле от 1 до 150 символов")
    private String name;

    @Max(value = 150, message = "Животные увы не могут быть старше 150 лет")
    @Min(value = 1, message = "Э")
    @NotNull(message = "напиши плиз")
    private Integer age;


    @Null(message = "Не нулл")
    @NotBlank(message = "Э")
    private String description;

    @Positive(message = "Масса должна быть больше нуля")
    @NotNull(message = "Э")
    private Integer mass;

//    @PositiveOrZero
//    @Negative
//    @NegativeOrZero
//
//    @DecimalMax()
//    @DecimalMin()
//
//    @Future
//    @FutureOrPresent
//    @Past
//    @PastOrPresent
//
//    @Email
//
//    @AssertFalse
//    @AssertTrue

    public Zoo()
    {

    }

    public Zoo(String name, Integer age, String description, Integer mass) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.mass = mass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }
}
