package com.readingisgood.bookstore.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllBooksDto {

    private Long id;
    private String name;
    private String author;
    private Integer stock;
    private Double price;

}
