package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.date.HolidayCalendarId;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class HolidayCalendarIdSoc extends StringBOT<HolidayCalendarId> {
    @Override
    public Object transformerFromExternalObject(HolidayCalendarId holidayCalendarId) throws DataException {
        return holidayCalendarId.getName();
    }

    @Override
    public HolidayCalendarId externalObjectFromTransformer(String name) throws DataException {
        return HolidayCalendarId.of(name);
    }

    @Override
    public Class<HolidayCalendarId> getExternalObjectClass() {
        return HolidayCalendarId.class;
    }
}
