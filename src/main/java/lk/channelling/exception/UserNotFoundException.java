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
 * Exception thrown when a user is not found in the system.
 *
 * <p>This exception is typically thrown when attempting to retrieve or
 * operate on a user entity that does not exist in the application's
 * user repository or database. It allows for more specific handling of
 * scenarios where a user cannot be found, and appropriate actions can
 * be taken in response to this exceptional condition.</p>
 *
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     // Code that may throw UserNotFoundException
 *     User user = userRepository.getUserById(userId);
 *     // Perform operations with the user
 * } catch (UserNotFoundException e) {
 *     // Handle the exception, e.g., log an error message or return an error response
 *     logger.error("User not found: {}", e.getMessage());
 * }
 * }</pre>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Constructs a new UserNotFoundException without any message.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Constructs a new UserNotFoundException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
