package com.example.website.service.impl;

import com.example.website.model.entity.Cart;
import com.example.website.model.entity.Product;
import com.example.website.model.entity.User;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.model.repository.UserRepository;
import com.example.website.model.service.CartServiceModel;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;

    private ProductServiceModel productServiceModel;
    private CartServiceModel cartServiceModel;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public CartServiceModel addProduct(Long id, Long userId) {
        Product product = this.productRepository.findById(id).get();


        //da probvam s harkodnato user id v controlera

        User user = this.userRepository.findById(userId).get();
        Long cartId = user.getCart().getId();

        Cart cart = this.cartRepository.findById(cartId).get();

        List<Product> productList = cart.getProducts();

        if (!productList.contains(product)){
            productList.add(product);
        }

       return modelMapper.map(this.cartRepository.saveAndFlush(cart), CartServiceModel.class);

    }

    @Override
    @Transactional
    public void removeProductFromCart(Long id, Long userId) {

        Product product = this.productRepository.findById(id).get();

        User user = this.userRepository.findById(userId).get();

        Cart cart = user.getCart();

        List<Product> products = cart.getProducts();


        if (products.contains(product)){
           cart.getProducts().remove(product);
        }


    }

    @Override
    @Transactional
    public void removeAllProductsFromCart(Long id) {

        User user = this.userRepository.findById(id).get();

        Cart cart = user.getCart();

        List<Product> productList = cart.getProducts();

        if (!productList.isEmpty()){
           productList.clear();
        }

    }

    @Override
    @Transactional
    public BigDecimal removeAllProductsFromCartAndReturnSum(Long id) {

        BigDecimal total = new BigDecimal(0);

        User user = this.userRepository.findById(id).get();

        Cart cart = user.getCart();

        List<Product> productList = cart.getProducts();

        for (int i = 0; i < productList.size(); i++) {
             total = total.add(productList.get(i).getPrice());
        }

        if (!productList.isEmpty()){
            productList.clear();
        }

        return total;

    }

    @Override
    public CartServiceModel getAllProducts(Long cartId) {

        Cart cart = cartRepository.findById(cartId).get();

        CartServiceModel cartServiceModel = new CartServiceModel();
        cartServiceModel.setId(cart.getId());

        List<ProductServiceModel> productList = new ArrayList<>();

        for (Product product:cart.getProducts()) {
            ProductServiceModel productServiceModel = new ProductServiceModel();
            productServiceModel.setId(product.getId());
            productServiceModel.setDescription(product.getDescription());
            productServiceModel.setName(product.getName());
            productServiceModel.setPrice(product.getPrice());
            productServiceModel.setGender(product.getGender());

            productList.add(productServiceModel);
        }

        cartServiceModel.setProductServiceModels(productList);

        return cartServiceModel;

    }


}
