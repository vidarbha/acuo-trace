package com.acuo.collateral.transform.trace.socs;

import com.acuo.common.model.trade.TradeAttributeType;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;

public class TradeAttributeTypeSoc extends StringBOT<TradeAttributeType> {
    public TradeAttributeTypeSoc() {
    }

    public Class<TradeAttributeType> getExternalObjectClass() {
        return TradeAttributeType.class;
    }

    public Object transformerFromExternalObject(TradeAttributeType externalObject) {
        return externalObject.toString();
    }

    public TradeAttributeType externalObjectFromTransformer(String transformerObject) {
        return TradeAttributeType.of(transformerObject) ;
    }
}