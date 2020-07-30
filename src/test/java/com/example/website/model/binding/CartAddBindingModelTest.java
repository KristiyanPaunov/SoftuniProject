package com.example.website.model.binding;

import com.example.website.model.entity.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartAddBindingModelTest {

   private CartAddBindingModel cartAddBindingModel = new CartAddBindingModel();

//   @Before
//   public void createBindingModel() {
//       this.cartAddBindingModel = new CartAddBindingModel();
//   }



    @Test
    void getId() {
        cartAddBindingModel.setId(1L);
        Assert.assertEquals(1L,cartAddBindingModel.getId().longValue());
    }

    @Test
    void setId() {
        cartAddBindingModel.setId(1L);
        Assert.assertEquals(1L,cartAddBindingModel.getId().longValue());
    }

    @Test
    void getProducts() {

        Assert.assertEquals(null,cartAddBindingModel.getProducts());
    }

    @Test
    void setProducts() {
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        cartAddBindingModel.setProducts(productList);

        Assert.assertEquals(1,cartAddBindingModel.getProducts().size());
    }
}