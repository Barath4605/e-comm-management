package in.ecomm.billing.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {
    private String name;
    private String description;
    private String bgColor;
}
