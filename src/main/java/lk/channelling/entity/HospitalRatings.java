package lk.channelling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "hospital_ratings")
@Data
public class HospitalRatings extends BaseEntity {

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating_date", nullable = false)
    private Timestamp ratingDate;
}
