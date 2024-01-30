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
package lk.channelling.exception;

/**
 * Exception thrown when an attempt is made to create or save an object that is too old.
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
public class OldObjectException extends RuntimeException {

    /**
     * Constructs an {@code OldObjectException}
     */
    public OldObjectException() {
        super();
    }

    /**
     * Constructs an {@code OldObjectException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public OldObjectException(String message) {
        super(message);
    }

}
