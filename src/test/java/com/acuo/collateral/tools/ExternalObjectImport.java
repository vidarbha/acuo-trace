package com.acuo.collateral.tools;

import com.acuo.collateral.transform.experiment.FixedConvention;
import com.acuo.collateral.transform.experiment.FixedIborConvention;
import com.acuo.collateral.transform.experiment.FloatConvention;
import com.acuo.collateral.transform.experiment.SwapTrade;
import com.tracegroup.transformer.actionrequests.ReqExternalObjectImport;
import com.tracegroup.transformer.tools.CmdLineProgressMeter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ExternalObjectImport {

    Class[] classes = {SwapTrade.class, FixedConvention.class, FixedIborConvention.class, FloatConvention.class};
    @Test
    public void importClass() {
        ReqExternalObjectImport req = new ReqExternalObjectImport ("External Import");
        req.setTpjFileName("./projects/valuation/valuation.tpj");
        req.setExternalObjectManagerDefinitionName("AcuoExternalObjectManager");
        List<Class> list = Arrays.asList(classes);
        for (Class aClass : list) {
            req.setClassName(aClass.getName());
            req.prepare();
            req.process(new CmdLineProgressMeter());
        }
    }

}
