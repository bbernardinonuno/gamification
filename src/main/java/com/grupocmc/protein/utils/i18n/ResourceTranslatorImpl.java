package com.grupocmc.protein.utils.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

@Component
public class ResourceTranslatorImpl implements ResourceTranslator {


    private MessageSource messageSource;

    @Autowired
    public ResourceTranslatorImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String resolve(String identifier) {

        return StringUtils.hasText(identifier) ? getMessage(identifier, null) : null;
    }

    @Override
    public List<String> resolve(List<String> identifier) {
        return identifier.stream().map(str -> resolve(str)).collect(toList());
    }

    @Override
    public String resolve(String identifier, String... replacers) {

        return StringUtils.hasText(identifier) ? getMessage(identifier, replacers) : null;

    }


    private String getMessage(String identifier, Object[] replace) {

        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedMessage = messageSource.getMessage(identifier, replace, currentLocale);

        if (!StringUtils.hasLength(localizedMessage)) {
            return "";
        }

        return localizedMessage;
    }

}
