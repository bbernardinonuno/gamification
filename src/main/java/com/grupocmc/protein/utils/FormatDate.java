package com.grupocmc.protein.utils;

import com.grupocmc.protein.config.Constantes;
import org.joda.time.DateTime;

import static org.joda.time.format.DateTimeFormat.forPattern;

public class FormatDate {

    public static DateTime formatUTC(String fechaInicio) {
        return forPattern(Constantes.FORMAT_UTC).withZoneUTC().parseDateTime(fechaInicio);
    }
}
