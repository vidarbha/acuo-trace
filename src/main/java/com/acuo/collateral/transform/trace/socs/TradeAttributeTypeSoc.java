package com.acuo.collateral.transform.trace.socs;

import com.acuo.common.model.trade.TradeAttributeType;
import com.acuo.common.type.TypedString;
import com.tracegroup.transformer.externalobjects.socs.DateTimeBOT;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.bots.DateTime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

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