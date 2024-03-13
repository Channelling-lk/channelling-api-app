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
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.channelling.enums.Gender;
import lk.channelling.enums.IdentificationMethod;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

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
@Table(name = "doctor")
public class Doctor extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Error: First Name is required.")
    @Size(max = 255, message = "Error: First Name must be less than {value} characters.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Error: Last Name is required.")
    @Size(max = 255, message = "Error: Last Name must be less than {value} characters.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 255, message = "Error: Display Name must be less than {value} characters.")
    @Column(name = "display_name", nullable = false)
    private String displayName;

    @NotBlank(message = "Error. Identification method is required.")
    @Size(max = 50, message = "Error: Identification Method must be less than {value} characters.")
    @Column(name = "identification_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentificationMethod identificationMethod;

    @NotBlank(message = "Error. Identification Value is required.")
    @Size(max = 20, message = "Error. Identification Value must be less than {value} characters.")
    @Column(name = "identification_value", nullable = false)
    private String identificationValue;

    @Size(max = 20, message = "Error. Mobile No 1 must be less than {value} digits.")
    @Column(name = "mobile_no1")
    private String mobileNo1;

    @Size(max = 20, message = "Error. Mobile No 2 must be less than {value} digits.")
    @Column(name = "mobile_no2")
    private String mobileNo2;

    @Size(max = 20, message = "Error. Email Address must be less than {value} digits.")
    @Column(name = "email")
    private String email;

    @Size(max = 255, message = "Error: Address Line 1 must be less than {value} characters.")
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Size(max = 255, message = "Error: Address Line 2 must be less than {value} characters.")
    @Column(name = "address_line2", nullable = false)
    private String addressLine2;

    @Size(max = 255, message = "Error: Address Line 3 must be less than {value} characters.")
    @Column(name = "address_line3", nullable = false)
    private String addressLine3;

    @Valid
    @NotNull(message = "Error: City ID is required.")
    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Size(max = 10, message = "Error. Gender must be less then {value} characters.")
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "Error. Doctor Registration No is required.")
    @Size(max = 100, message = "Error: Doctor Registration No must be less than {value} characters.")
    @Column(name = "doc_reg_no", nullable = false)
    private String doctorRegistrationNo;

    @Valid
    @NotNull(message = "Error: Title ID is required.")
    @Column(name = "title_id", nullable = false)
    private Long titleId;
}
