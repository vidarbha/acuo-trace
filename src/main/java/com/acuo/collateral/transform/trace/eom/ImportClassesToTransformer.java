package com.acuo.collateral.transform.trace.eom;

import com.acuo.collateral.transform.inputs.Envelop;
import com.acuo.common.model.product.fx.FxSwap;
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

public class ImportClassesToTransformer {

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

    private static Class[] toImport = {Envelop.class};

    public static void main(String[] args) {
        ReqExternalObjectImport req = new ReqExternalObjectImport ("External Import");
        req.setTpjFileName("./projects/markit/markit.tpj");
        req.setExternalObjectManagerDefinitionName("AcuoExternalObjectManager");

        List<Class> classes = Arrays.asList(toImport);
        for (Class aClass : classes) {
            req.setClassName(aClass.getName());
            req.prepare();
            req.process(new CmdLineProgressMeter());
        }
    }
}
