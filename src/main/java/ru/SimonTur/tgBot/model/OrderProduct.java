package ru.SimonTur.tgBot.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private ClientOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count_product")
    private Integer countProduct;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ClientOrder getOrder() { return order; }
    public void setOrder(ClientOrder order) { this.order = order; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getCountProduct() { return countProduct; }
    public void setCountProduct(Integer countProduct) { this.countProduct = countProduct; }
}