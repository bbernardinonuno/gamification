package com.grupocmc.protein.validators;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidUtcDateImpl implements ConstraintValidator<ValidUtc, String> {

    DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:Z").withZoneUTC();

    DateTimeFormatter dateTimeFormatWithSeconds = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:SS:ZZ").withZoneUTC();

    @Override
    public void initialize(ValidUtc validUtc) {

    }

    @Override
    public boolean isValid(String dateToValidate, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.hasText(dateToValidate)) {
                dateTimeFormat.parseDateTime(dateToValidate);
            }
            return true;
        } catch (Exception e) {
            try{
                dateTimeFormatWithSeconds.parseDateTime(dateToValidate);
                return true;
            } catch (Exception e1) {
                return false;
            }
        }
    }
}