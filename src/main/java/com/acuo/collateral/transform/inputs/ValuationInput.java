package com.acuo.collateral.transform.inputs;

import com.acuo.collateral.transform.TransformerContext;
import lombok.Data;

import java.util.List;

@Data
public class ValuationInput {

    private List<Envelop> envelops;
    private TransformerContext context;
}