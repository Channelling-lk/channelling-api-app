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

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date and time operations.
 *
 * <p>This utility class provides methods for common date and time operations,
 * such as parsing, formatting, and manipulation. It serves as a centralized
 * point for managing date and time-related functionalities within the application.</p>
 *
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * DateTimeHandler dateTimeHandler = new DateTimeHandler();
 * LocalDateTime currentDateTime = dateTimeHandler.getCurrentDateTime();
 * String formattedDate = dateTimeHandler.formatDate(currentDateTime, "yyyy-MM-dd");
 * }</pre>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
public class DateTimeHandler {

    /**
     * Default pattern to format date time.
     */
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-mm-dd hh:mm:ss";

    /**
     * Gets the current date and time.
     *
     * @return The current date and time as a {@code LocalDateTime} object.
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Formats a given date and time using the specified pattern or using default pattern.
     *
     * @param dateTime The date and time to be formatted.
     * @param pattern  The pattern to use for formatting (e.g., "yyyy-MM-dd HH:mm:ss").
     * @return The formatted date and time as a {@code String}.
     */
    public static String formatDate(LocalDateTime dateTime, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = DEFAULT_DATE_TIME_FORMAT;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * Formats the current date and time using the specified pattern or using default pattern.
     *
     * @return The formatted date and time as a {@code String}.
     */
    public static String formatCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * Parses a {@code String} into a {@code LocalDateTime} object using the specified pattern.
     *
     * @param dateString The {@code String} representation of the date and time.
     * @param pattern    The pattern to use for parsing (e.g., "yyyy-MM-dd HH:mm:ss").
     * @return The parsed {@code LocalDateTime} object.
     * @throws java.time.format.DateTimeParseException If the input string cannot be parsed.
     */
    public static LocalDateTime parseDate(String dateString, String pattern) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return LocalDateTime.parse(dateString, formatter);
    }
}

