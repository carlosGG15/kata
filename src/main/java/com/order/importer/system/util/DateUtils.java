package com.order.importer.system.util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.order.importer.system.exception.DateFormatException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

    private static final String REGEX = "(?<month>\\d+)\\/(?<day>\\d+)\\/(?<year>\\d+)";
    private static final Pattern pattern = Pattern.compile(REGEX);

    public LocalDate mapDate(final String dateString) {

        final Matcher matcher = pattern.matcher(dateString);
        final boolean match = matcher.find();
        if (match) {
            final int day = Integer.parseInt(matcher.group("day"));
            final int month = Integer.parseInt(matcher.group("month"));
            final int year = Integer.parseInt(matcher.group("year"));

            return LocalDate.of(year, month, day);
        } else {
            throw new DateFormatException("Wrong date format exception ");
        }

    }

}
