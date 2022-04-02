package com.liga.store.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The class describes the "Good" entity.
 * The @Entity annotation says that objects of this class will be processed by hibernate.
 * The @Getter and @Setter annotation generates getters and setters for all fields.
 * The @NoArgsConstructor annotation generates no-args constructor.
 * The @AllArgsConstructor annotation generates all args constructor.
 * The @EqualsAndHashCode annotation generates implementations for the {@code equals} and {@code hashCode} methods inherited
 * by all objects, based on relevant fields.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "good")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "good", "goodTitle", "price"})
public class Good {
    /**
     * The unique code of the object.
     * The @Id annotation says that the field is the key for the current object.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * Good title.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of good title field is 255 characters.
     */
    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String title;


    /**
     * Good manufacturer.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of good manufacturer field is 255 characters.
     */
    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String producer;

    /**
     * The year the good was released.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotNull(message = "Пожалуйста заполните поле")
    private Integer year;

    /**
     * Manufacturer country.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String country;

    /**
     * Good description.
     */
    private String description;

    /**
     * Good price.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotNull(message = "Пожалуйста заполните поле")
    private Integer price;

    @NotNull(message =  "Пожалуйста заполните поле")
    private Integer quantity;

    /**
     * Type of good.
     * Max length of manufacturer country field is 255 characters.
     */
    @Length(max = 255)
    private String type;
}