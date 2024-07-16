package challenge.backend.vendingmachine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String productName;
    private Integer amountAvailable;
    private Integer cost;
    private String sellerId;
}
