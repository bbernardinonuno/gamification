package com.grupocmc.protein.mvc.deserializers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class JodaTimeDeserializer extends StdSerializer<DateTime> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy'T'HH:mm:ss:Z").withZoneUTC();

    public JodaTimeDeserializer() {
        super(DateTime.class);
    }

    protected JodaTimeDeserializer(Class<DateTime> aClass) {
        super(aClass);
    }

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeString(dateTimeFormatter.print(dateTime));
    }
}
