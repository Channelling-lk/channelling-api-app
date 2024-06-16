package lk.channelling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rating_categories")
@Data
public class RatingCategory extends BaseEntity {

    @Column(name = "category_name", nullable = false)
    private String categoryName;
}
