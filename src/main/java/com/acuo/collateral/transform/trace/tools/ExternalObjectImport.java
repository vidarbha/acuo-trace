package com.acuo.collateral.transform.trace.tools;

import com.acuo.collateral.transform.inputs.ValuationInput;
import com.acuo.common.model.agreements.Agreement;
import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.basics.date.BusinessDayConvention;
import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.basics.date.HolidayCalendarId;
import com.opengamma.strata.basics.schedule.Frequency;
import com.opengamma.strata.basics.schedule.RollConvention;
import com.opengamma.strata.product.common.PayReceive;
import com.opengamma.strata.product.swap.FixingRelativeTo;
import com.tracegroup.transformer.actionrequests.ReqExternalObjectImport;
import com.tracegroup.transformer.tools.CmdLineProgressMeter;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ExternalObjectImport {

    @Data
    public static class Test {
        private BusinessDayConvention businessDayConvention;
        private Currency currency;
        private DayCount dayCount;
        private FixingRelativeTo fixingRelativeTo;
        private Frequency frequency;
        private HolidayCalendarId holidayCalendarId;
        private LocalDate localDate;
        private PayReceive payReceive;
        private RollConvention rollConvention;
    }

    private static Class[] classes = {ValuationInput.class};

    public static void main(String[] args) {
        ReqExternalObjectImport req = new ReqExternalObjectImport ("External Import");
        req.setTpjFileName("./projects/global/global.tpj");
        req.setExternalObjectManagerDefinitionName("AcuoExternalObjectManager");
        List<Class> list = Arrays.asList(classes);
        for (Class aClass : list) {
            req.setClassName(aClass.getName());
            req.prepare();
            req.process(new CmdLineProgressMeter());
        }
    }

}
