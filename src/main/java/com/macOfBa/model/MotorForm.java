package com.macOfBa.model;

import org.springframework.web.multipart.MultipartFile;

public class MotorForm {
    private Long id;
    private String name;
    private double price;
    private MultipartFile image;

    public MotorForm() {
    }

    public MotorForm(Long id, String name, double price, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public MotorForm(String name, double price, MultipartFile image) {
        this.name = name;
        this.price = price;
        this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MotorForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }
}
