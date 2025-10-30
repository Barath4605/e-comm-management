package in.ecomm.billing.service;

import in.ecomm.billing.dtos.CategoryRequest;
import in.ecomm.billing.dtos.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest request, MultipartFile file);

    List<CategoryResponse> read();

    void delete(String categoryId);

    CategoryResponse getOneCategory(String categoryId);
}
