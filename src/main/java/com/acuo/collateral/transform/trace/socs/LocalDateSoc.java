package com.acuo.collateral.transform.trace.socs;

import com.tracegroup.transformer.externalobjects.socs.DateTimeBOT;
import com.tracegroup.transformer.mom.bots.DateTime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateSoc extends DateTimeBOT<LocalDate> {
    public LocalDateSoc() {
    }

    public Class<LocalDate> getExternalObjectClass() {
        return LocalDate.class;
    }

    public Object transformerFromExternalObject(LocalDate externalObject) {
        Instant instant = externalObject.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return new DateTime(instant.toEpochMilli());
    }

    public LocalDate externalObjectFromTransformer(DateTime transformerObject) {
        return Instant.ofEpochMilli(transformerObject.getTimeOnTimeLine()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}