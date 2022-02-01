package com.example.springmarket.Dto.cart;


public class AddToCartDto {

    /*private Integer id;*/
    private Integer productId;
    private Integer quantity;

    public AddToCartDto() {
    }

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AddToCartDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }


    public AddToCartDto(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
