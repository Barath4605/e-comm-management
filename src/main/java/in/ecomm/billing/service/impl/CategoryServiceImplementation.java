package in.ecomm.billing.service.impl;

import in.ecomm.billing.CategoryRepository.CategoryRepository;
import in.ecomm.billing.entity.CategoryEntity;
import in.ecomm.billing.dtos.CategoryRequest;
import in.ecomm.billing.dtos.CategoryResponse;
import in.ecomm.billing.service.CategoryService;
import in.ecomm.billing.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponse add(CategoryRequest request, MultipartFile file) {
        CategoryEntity newCategory = convertToEntity(request);
        String imgUrl = fileUploadService.uploadFile(file);
        newCategory = categoryRepository.save(newCategory);
        newCategory.setImgUrl(imgUrl);
        return convertToResponse(newCategory);

    }

    @Override
    public List<CategoryResponse> read() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
        CategoryEntity existingEntity = categoryRepository.findAllByCategoryId(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));

        fileUploadService.deleteFile(existingEntity.getImgUrl());
        categoryRepository.delete(existingEntity);
    }

    @Override
    public CategoryResponse getOneCategory(String categoryId) {
        CategoryEntity specCategory = categoryRepository.findByCategoryId(categoryId);
        return convertToResponse(specCategory);
    }


    private CategoryEntity convertToEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();

    }
    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        return CategoryResponse.builder()
                .categoryId(String.valueOf(newCategory.getCategoryId()))
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .build();
    }


}
