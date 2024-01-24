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
 * Custom exception class representing a situation where no content is available.
 * This can be used, for example, in cases where a query or operation did not result in any data.
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
public class NoContentException extends RuntimeException {
    /**
     * Constructs a new NoContentException
     */
    public NoContentException() {
        super();
    }

    /**
     * Constructs a new NoContentException with the specified detail message.
     *
     * @param message the detail message.
     */
    public NoContentException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoContentException with the specified detail message and a
     * nested exception.
     *
     * @param message the detail message.
     * @param cause   the nested exception.
     */
    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
