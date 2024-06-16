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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents an Institution with its details.
 *
 * <p>This entity class hold information such as the institution code, name, and other relevant details.
 * It is designed to be used in conjunction to persist and retrieve institution information.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@JsonSerialize
@Entity
@Data
@Table(name = "doctor_speciality")
public class DoctorSpeciality extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "Error: Doctor Name is required.")
    @Column(name = "doctor_id", nullable = false)
    @JsonProperty("doctor_id")
    private Long doctorId;

    @Valid
    @NotNull(message = "Error: Speciality is required.")
    @Column(name = "speciality_id", nullable = false)
    @JsonProperty("speciality_id")
    private Long specialityId;
}
