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
package lk.channelling.enums;

/**
 * Enum representing the status of an entity, such as ACTIVE or INACTIVE.
 * <p></p>
 * <p>This enum class is used to define different status that an entity can be in, commonly used for representing the
 * activation status , availability or similar characteristics.</p>
 * <p></p>
 * <p>Usage Example :</p>
 * <pre> {@code
 * Status entityStatus = Status.ACTIVE;
 * }</pre>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
public enum Status {

    /* Represents the active status */
    ACTIVE,

    /* Represents the inactive status */
    INACTIVE;
}
