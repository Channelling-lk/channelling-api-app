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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lk.channelling.enums.Status;
import lombok.Data;

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

@Entity
@Data
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonIgnore
    protected Long id;

    @NotBlank(message = "Code is required.")
    @Size(max = 10, message = "Code should be less than {value} characters.")
    private String code;

    @NotBlank(message = "Description is required.")
    @Size(max = 100, message = "Description should be less than {value} characters.")
    private String description;

    @NotBlank(message = "Iso Code is required")
    @Size(max = 10, message = "Iso Code should be less than {value} characters.")
    @JsonProperty("iso_code")
    @Column(name = "iso_code")
    private String isoCode;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonProperty("created_user")
    @Column(name = "created_user")
    private String createdUser;

    @JsonProperty("created_date")
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("modified_user")
    @Column(name = "modified_user")
    private String modifiedUser;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("modified_date")
    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    @JsonIgnore
    @Version
    private Long version;
}
