package com.solbegsoft.beersapi.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Message utils, return localized messages
 */
@Component
@RequiredArgsConstructor
public class MessageUtils {

    /**
     * @see MessageSource
     */
    private final MessageSource messageSource;

    /**
     * @param code code from localized resource bundle
     * @param objects parameters for messages
     * @param locale  Locale of input params
     * @return String
     */
    public String getMessage(String code, Object[] objects, Locale locale) {
        return messageSource.getMessage(code, objects, locale);
    }

    /**
     * @param code   code from localized resource bundle
     * @param locale Locale
     * @return String
     */
    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }
}