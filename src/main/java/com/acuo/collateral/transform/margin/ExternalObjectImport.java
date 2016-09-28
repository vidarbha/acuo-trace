package com.acuo.collateral.transform.margin;

import com.acuo.common.model.margin.MarginCall;
import com.tracegroup.transformer.actionrequests.ReqExternalObjectImport;
import com.tracegroup.transformer.tools.CmdLineProgressMeter;

import java.util.Arrays;
import java.util.List;

public class ExternalObjectImport {

    private static Class[] classes = {MarginCall.class};

    public static void main(String[] args) {
        ReqExternalObjectImport req = new ReqExternalObjectImport ("External Import");
        req.setTpjFileName("./projects/margin/margin.tpj");
        req.setExternalObjectManagerDefinitionName("AcuoExternalObjectManager");
        List<Class> list = Arrays.asList(classes);
        for (Class aClass : list) {
            req.setClassName(aClass.getName());
            req.prepare();
            req.process(new CmdLineProgressMeter());
        }
    }

}
