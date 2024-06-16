package lk.channelling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_ratings")
@Data
public class DoctorRating extends BaseEntity implements Serializable {

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating_date", nullable = false)
    private LocalDateTime ratingDate;
}
