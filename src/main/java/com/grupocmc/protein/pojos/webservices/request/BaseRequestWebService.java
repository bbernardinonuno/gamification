package com.grupocmc.protein.pojos.webservices.request;

import com.google.common.base.Splitter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.io.Serializable;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

@Data
@NoArgsConstructor
public class BaseRequestWebService implements Serializable {

    private String accessToken;
    private String locale;
    private String user;

    public BaseRequestWebService withHeader(HttpHeaders header) {
        this.setAccessToken(header.getFirst("token"));
        this.setAccessToken(header.getFirst("user"));
        this.setLocale(transformLenguage(header.getFirst(ACCEPT_LANGUAGE)));
        return this;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    private String transformLenguage(String acceptLanguage) {
        if (StringUtils.hasText(acceptLanguage)) {
            return Splitter.on(",").on("-").
                    splitToList(acceptLanguage).get(0).toLowerCase();
        }
        return acceptLanguage;

    }
}

