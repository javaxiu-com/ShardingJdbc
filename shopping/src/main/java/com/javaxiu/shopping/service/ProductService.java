package com.javaxiu.shopping.service;

import com.javaxiu.shopping.entity.ProductInfo;

import java.util.List;

/**
 * Created by Administrator.
 */
public interface ProductService {
    //添加商品
    public void createProduct(ProductInfo product);

    //查询商品
    public List<ProductInfo> queryProduct(int page, int pageSize);
}
