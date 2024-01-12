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
package lk.channelling.handlers;

import lk.channelling.exception.UserNotFoundException;
import lombok.Getter;
import lombok.Setter;

/**
 * Utility class for handling login authentication and storing the authenticated username.
 *
 * <p>This utility class follows the Singleton design pattern, ensuring that
 * only one instance of the class is created. It provides methods for handling
 * login authentication and storing the authenticated username. The class is
 * designed to be used in scenarios where user authentication needs to be tracked
 * within the application.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
public class LoginAuthenticationHandler {

    /**
     * The singleton instance of the class.
     */
    private static LoginAuthenticationHandler instance;

    /**
     * The username.
     */
    @Getter
    @Setter
    private static String userName;

    /**
     * Retrieves the singleton instance of the class. If the instance does not
     * exist, it is created for the first time.
     *
     * @return The singleton instance of the class.
     */
    public static LoginAuthenticationHandler getInstance() {
        if (instance == null) synchronized (LoginAuthenticationHandler.class) {
            if (instance == null) {
                instance = new LoginAuthenticationHandler();
            }
        }
        return instance;
    }

    public static void validateUser() {
        if (userName == null) throw new UserNotFoundException();
    }

}
