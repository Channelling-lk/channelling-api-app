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
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents a Country with its details.
 *
 * <p>This entity class hold information such as the country code, name, its iso code and other relevant details.
 * It is designed to be used in conjunction to persist and retrieve country information.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */

@JsonSerialize
@Entity
@Data
@Table(name = "user_profile")
public class UserProfile extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "first_name", nullable = false, unique = true)
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "display_name")
    @JsonProperty("display_name")
    private String displayName;

    @Column(name = "identification_method", nullable = false)
    @JsonProperty("identification_method")
    private String identificationMethod;

    @Column(name = "identification_value", nullable = false, unique = true)
    @JsonProperty("identification_value")
    private String identificationValue;

    @Column(name = "mobile_no")
    @JsonProperty("mobile_no")
    private String mobileNo;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "address_line_1")
    @JsonProperty("address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    @JsonProperty("address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    @JsonProperty("address_line_3")
    private String addressLine3;

    @Column(name = "date_of_birth")
    @JsonProperty("date_of_birth")
    private Timestamp dateOfBirth;

    @Column(name = "gender")
    @JsonProperty("gender")
    private String gender;

    @Column(name = "profile_picture")
    @JsonProperty("profile_picture")
    private String profilePicture;


}
