package com.acuo.collateral.transform.trace.socs;

import com.tracegroup.transformer.externalobjects.socs.DateTimeBOT;
import com.tracegroup.transformer.mom.bots.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeSoc extends DateTimeBOT<LocalDateTime> {
    public LocalDateTimeSoc() {
    }

    public Class<LocalDateTime> getExternalObjectClass() {
        return LocalDateTime.class;
    }

    public Object transformerFromExternalObject(LocalDateTime externalObject) {
        Instant instant = externalObject.atZone(ZoneId.systemDefault()).toInstant();
        return new DateTime(instant.toEpochMilli());
    }

    public LocalDateTime externalObjectFromTransformer(DateTime transformerObject) {
        return Instant.ofEpochMilli(transformerObject.getTimeOnTimeLine()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}