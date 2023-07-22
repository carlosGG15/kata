package com.order.importer.system.util;

import com.order.importer.system.exception.DateFormatException;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateUtilsTest {

    private static final LocalDate DATE = LocalDate.of(2023,7, 10);

    @Test
    void givenValidDateString_whenMapDate_thenRetrievedDate() {
        final String dateString = "7/10/2023";
        final LocalDate localDate = DateUtils.mapDate(dateString);
        assertEquals(DATE, localDate);

    }

    @ParameterizedTest(name = "format date {0}")
    @MethodSource("invalidDateString")
    void givenInvalidDateString_whenMapDate_thenRetrieveValidDate(final String dateString) {
        assertThrows(DateFormatException.class, () -> DateUtils.mapDate(dateString));
    }

    private static Stream<Arguments> invalidDateString() {
        return Stream.of(
                Arguments.of("7/27s/2012"),
                Arguments.of("s7/27s/2012"),
                Arguments.of("7/2/s2012"),
                Arguments.of("7s/27/2012")
        );
    }

    @ParameterizedTest(name = "format date {0}")
    @MethodSource("invalidDateValue")
    void givenInvalidDateValue_whenMapDate_thenExceptionThrown(final String dateString) {
        assertThrows(DateTimeException.class, () -> DateUtils.mapDate(dateString));
    }

    private static Stream<Arguments> invalidDateValue() {
        return Stream.of(
                Arguments.of("7/50/2012"),
                Arguments.of("127/7/2012")
        );
    }
}
