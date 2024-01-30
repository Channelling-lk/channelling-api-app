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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lk.channelling.enums.Status;
import lombok.Data;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

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

    @Version
    private Long version;
}
