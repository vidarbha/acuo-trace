package com.acuo.collateral.transform.trace.socs;

import com.acuo.common.util.ArgChecker;
import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.basics.date.HolidayCalendarId;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

import static com.acuo.common.util.ArgChecker.*;

public class DayCountSoc extends StringBOT<DayCount> {
    @Override
    public Object transformerFromExternalObject(DayCount dayCount) throws DataException {
        return dayCount.getName();
    }

    @Override
    public DayCount externalObjectFromTransformer(String name) throws DataException {
        name = notNull(name, "name");
        if (name.startsWith("Bus/252")) {
            String holidayName = name.substring(8);
            holidayName= notBlank(holidayName, "holiday calendar must should be specify after Bus/252");
            HolidayCalendarId holiday = HolidayCalendarId.of(holidayName);
            return DayCount.ofBus252(holiday);
        } else return DayCount.of(name);
    }

    @Override
    public Class<DayCount> getExternalObjectClass() {
        return DayCount.class;
    }

    @Override
    public boolean canProvideSOCForClass(Class clazz) {
        return DayCount.class.isAssignableFrom(clazz);
    }
}
