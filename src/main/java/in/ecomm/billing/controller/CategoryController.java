package in.ecomm.billing.controller;

import in.ecomm.billing.dtos.CategoryRequest;
import in.ecomm.billing.dtos.CategoryResponse;
import in.ecomm.billing.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse getCategory(@RequestBody CategoryRequest request) {
        return categoryService.add(request);
    }

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return categoryService.read();
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getOneCategory(@PathVariable String categoryId) {
        return categoryService.getOneCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable String categoryId) {
        try {
            categoryService.delete(categoryId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found : " + categoryId);
        }
    }

}
