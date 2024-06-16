package lk.channelling.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("patient_id")
    private Long patientId;

    @Column(name = "session_id", nullable = false)
    @JsonProperty("session_id")
    private Long sessionId;

    @Column(name = "doctor_id", nullable = false)
    @JsonProperty("doctor_id")
    private Long doctorId;

    @Column(name = "category_id", nullable = false)
    @JsonProperty("category_id")
    private Long categoryId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating_date", nullable = false)
    @JsonProperty("rating_date")
    private LocalDateTime ratingDate;
}
