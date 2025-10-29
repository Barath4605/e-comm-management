package in.ecomm.billing.service;

import in.ecomm.billing.dtos.CategoryRequest;
import in.ecomm.billing.dtos.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest request);

    List<CategoryResponse> read();

    void delete(String categoryId);

    CategoryResponse getOneCategory(String categoryId);
}
