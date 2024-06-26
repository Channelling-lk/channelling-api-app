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

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class DefinitionBaseEntity extends BaseEntity {

    @NotBlank(message = "Error: Code is required.")
    @Size(max = 10, message = "Error: Code must be less than {value} characters.")
    @Pattern(regexp = "^$|^[a-zA-Z0-9]+$", message = "Error: Invalid Input. Please use only numbers and characters.")
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Error: Description is required.")
    @Size(max = 100, message = "Error: Description must be less than {value} characters.")
    @Column(name = "description", nullable = false)
    private String description;
}
