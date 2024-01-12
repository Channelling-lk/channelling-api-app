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
package lk.channelling.log;

import lk.channelling.handlers.DateTimeHandler;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

/**
 * Utility class for handling requests within the application.
 *
 * <p>This class provides capability to log various types of information, debug messages, informational messages,
 * warnings, and errors.It serves as a centralized logging facility to capture and record relevant information
 * about the application's runtime behaviors.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Log4j2
@Setter
public class LogRequest {

    private String requestId;
    private static LogRequest instance;
    private final String DATE_AND_TIME = "Date & Time : ";
    private final String API = "Api : ";
    private final String CLASS_NAME = "Class Name : ";
    private final String ENTER_METHOD = "Enter Method : ";
    private final String ARGS = "Args : ";
    private final String SPACE = " ";

    /**
     * Retrieves the singleton instance of the class. If the instance does not exist, it is created for the first time.
     *
     * @return The singleton instance of the class.
     */
    public static LogRequest getInstance() {
        if (instance == null) {
            synchronized (LogRequest.class) {
                if (instance == null) {
                    instance = new LogRequest();
                }
            }
        }
        return instance;
    }

    /**
     * Utility method for logging messages with various log levels.
     *
     * <p>This utility method allows for dynamic logging of messages with different
     * log levels. It accepts parameters such as log level, API name, class name,
     * method name, and a string argument to construct a log message. The actual
     * logging is performed using an underlying logging framework.</p>
     *
     * @param logLevel   The log level for the message (e.g. INFO, WARN, ERROR).
     * @param apiName    The name of the API or module associated with the log message.
     * @param className  The name of the class where log message originates.
     * @param methodName The name of the method where the log message originates.
     * @param argument   The arguments of the method where the log message originates.
     */
    public void log(Level logLevel, String apiName, String className, String methodName, String argument) {
        StringBuilder message = new StringBuilder();

        message.append(requestId).append(SPACE).append(DATE_AND_TIME).append(DateTimeHandler.formatCurrentDateTime()).append(SPACE).append(API).append(apiName).append(SPACE).append(CLASS_NAME).append(className).append(SPACE).append(ENTER_METHOD).append(methodName).append(SPACE).append(ARGS).append(argument);

        log.log(logLevel, message.toString());
    }
}
