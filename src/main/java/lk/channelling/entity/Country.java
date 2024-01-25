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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

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
@Table(name = "country")
public class Country extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
