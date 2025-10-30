package in.ecomm.billing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.ecomm.billing.dtos.CategoryRequest;
import in.ecomm.billing.dtos.CategoryResponse;
import in.ecomm.billing.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    public CategoryController(CategoryService categoryService, ObjectMapper objectMapper) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse getCategory(@RequestPart("category") String categoryString,
                                        @RequestPart("file") MultipartFile file ) {

        ObjectMapper mapper = new ObjectMapper();
        CategoryRequest request = null;

        try {
            request = objectMapper.readValue(categoryString, CategoryRequest.class);
            return categoryService.add(request, file);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

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
