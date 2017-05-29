package com.acuo.collateral.transform.trace.socs;

import com.tracegroup.transformer.externalobjects.socs.DateTimeBOT;
import com.tracegroup.transformer.mom.bots.DateTime;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class LocalTimeSoc extends DateTimeBOT<LocalTime> {
    public LocalTimeSoc() {
    }

    public Class<LocalTime> getExternalObjectClass() {
        return LocalTime.class;
    }

    public Object transformerFromExternalObject(DateTime externalObject) {
        Instant instant = externalObject.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return new DateTime(instant.toEpochMilli());
    }

    public LocalTime externalObjectFromTransformer(DateTime transformerObject) {
        return Instant.ofEpochMilli(transformerObject.getTimeOnTimeLine()).atZone(ZoneId.systemDefault()).toLocalTime();
    }
}
