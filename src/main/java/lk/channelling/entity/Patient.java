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
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "patient")
@JsonSerialize
public class Patient extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "identification_method", nullable = false)
    private String identificationMethod;

    @Column(name = "identification_value", nullable = false)
    private String identificationValue;

    @Column(name = "mobile_no1")
    private String mobileNo1;

    @Column(name = "mobile_no2")
    private String mobileNo2;

    @Column(name = "email")
    private String email;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "address_line3")
    private String addressLine3;

    @Column(name = "city_id", nullable = false)
    private int cityId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "title_id")
    private int titleId;

}
