package com.solbegsoft.beersapi.utils;


import com.solbegsoft.beersapi.configurations.ErrorMessageConstant;
import com.solbegsoft.beersapi.exceptions.ResponseBeersException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Converter First Brewed field
 */
@Data
@Component
@RequiredArgsConstructor
public class FirstBrewedConverter {

    /**
     * @see MessageUtils
     */
    private final MessageUtils messageUtils;

    /**
     * Converter String type format date to LocalDate type
     *
     * @param date in String type format
     * @return {@link LocalDate}
     */
    public LocalDate convertStringToLocalDate(String date) throws ResponseBeersException {

        if (Objects.nonNull(date)) {
            try {
                int year = 1;
                int mounth = 1;
                int day = 1;
                if (date.length() == 4) {
                    return LocalDate.of(Integer.parseInt(date), 1, 1);
                } else if (date.length() == 7) {
                    year = Integer.parseInt(date.substring(3, 7));
                    mounth = Integer.parseInt(date.substring(0, 2));
                }
                return LocalDate.of(year, mounth, day);
            } catch (RuntimeException e) {
                throw new ResponseBeersException(ErrorMessageConstant.ERROR_CONVERTER, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }

    /**
     * Converter LocalDate type format date to String type
     *
     * @param date LocalDate
     * @return String
     */
    public String convertLocalDateToString(LocalDate date) {

        return String.format("{0}/{1}", date.getMonth(), date.getYear());
    }
}