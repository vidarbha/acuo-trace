package com.acuo.collateral.transform.trace.tools;

import com.acuo.common.model.results.MSError;
import com.acuo.common.model.trade.SwapTrade;
import com.tracegroup.transformer.actionrequests.ReqExternalObjectImport;
import com.tracegroup.transformer.tools.CmdLineProgressMeter;

import java.util.Arrays;
import java.util.List;

public class ExternalObjectImport {

    private static Class[] classes = {MSError.class/*, FixedConvention.class, FixedIborConvention.class, FloatConvention.class*/};

    public static void main(String[] args) {
        ReqExternalObjectImport req = new ReqExternalObjectImport ("External Import");
        req.setTpjFileName("./projects/agreement/Agreement.tpj");
        req.setExternalObjectManagerDefinitionName("AcuoExternalObjectManager");
        List<Class> list = Arrays.asList(classes);
        for (Class aClass : list) {
            req.setClassName(aClass.getName());
            req.prepare();
            req.process(new CmdLineProgressMeter());
        }
    }

}
