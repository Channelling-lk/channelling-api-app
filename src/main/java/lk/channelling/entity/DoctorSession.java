/*
 * Copyright 2024 Channelling.lk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lk.channelling.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "doctor_sessions")
public class DoctorSession extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "doctor_id", nullable = false)
    @JsonProperty("doctor_id")
    private int doctorId;

    @Column(name = "hospital_id", nullable = false)
    @JsonProperty("hospital_id")
    private int hospitalId;

    @Column(name = "session_date_time")
    @JsonProperty("session_date_time")
    private LocalDateTime sessionDateTime;

    @Column(name = "max_patients")
    @JsonProperty("max_patients")
    private int maxPatients;

    @Column(name = "total_fee")
    @JsonProperty("total_fee")
    private double totalFee;
}
