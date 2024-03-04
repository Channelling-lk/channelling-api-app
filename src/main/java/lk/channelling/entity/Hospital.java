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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "hospital")
public class Hospital extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Error: Name is required.")
    @Size(max = 255, message = "Error: Name must be less than {value} characters.")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255, message = "Error: Display Name must be less than {value} characters.")
    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Size(max = 100, message = "Error: Business Registration No must be less than {value} characters.")
    @Column(name = "br_no", nullable = false)
    private String brNo;

    @Size(max = 255, message = "Error: Address Line 1 must be less than {value} characters.")
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Size(max = 255, message = "Error: Address Line 2 must be less than {value} characters.")
    @Column(name = "address_line2", nullable = false)
    private String addressLine2;

    @Size(max = 255, message = "Error: Address Line 3 must be less than {value} characters.")
    @Column(name = "address_line3", nullable = false)
    private String addressLine3;

    @Column(name = "hospital_fee")
    private Double hospitalFee;

    @Valid
    @NotNull(message = "Error: City ID is required.")
    @Column(name = "city_id", nullable = false)
    private Long cityId;
}
