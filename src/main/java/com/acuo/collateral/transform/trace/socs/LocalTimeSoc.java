package com.acuo.collateral.transform.trace.socs;

import com.tracegroup.transformer.externalobjects.socs.TimeBOT;
import com.tracegroup.transformer.mom.bots.Time;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class LocalTimeSoc extends TimeBOT<LocalTime> {
    public LocalTimeSoc() {
    }

    public Class<LocalTime> getExternalObjectClass() {
        return LocalTime.class;
    }

    public Object transformerFromExternalObject(Time externalObject) {
        Instant instant = externalObject.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return new Time(instant.toEpochMilli());
    }

    public LocalTime externalObjectFromTransformer(Time transformerObject) {
        return Instant.ofEpochMilli(transformerObject.getTimeOnTimeLine()).atZone(ZoneId.systemDefault()).toLocalTime();
    }
}
