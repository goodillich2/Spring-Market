package com.example.springmarket.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "market")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "picture_url")
    private String pictureUrl;

}
