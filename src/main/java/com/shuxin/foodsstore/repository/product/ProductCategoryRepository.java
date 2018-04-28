package com.shuxin.foodsstore.repository.product;

import com.shuxin.foodsstore.entity.ProductCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liu yang
 * @ClassName: ProductCategoryRepository
 * @Description: TODO(商品类目数据库操作.)
 * @date 2017/12/25 10:14
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {


    List<ProductCategory> findByCategorySerialIn(List<Integer> categorySerialList);


    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);

    List<ProductCategory> findAllByCategoryModel(Integer categoryModel);

    //排序查询
    List<ProductCategory> findAllByCategoryModel(Sort sort,Integer categoryModel);


}
