package in.ecomm.billing.CategoryRepository;

import in.ecomm.billing.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findAllByCategoryId(String categoryId);
    CategoryEntity findByCategoryId(String categoryId);
}
