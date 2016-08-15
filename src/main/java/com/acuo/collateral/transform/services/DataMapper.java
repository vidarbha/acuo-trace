package com.acuo.collateral.transform.services;

import com.acuo.common.model.IrSwap;
import com.acuo.common.model.Request;

import java.util.List;

public interface DataMapper {

    List<IrSwap> fromCmeFile(String data);

    String toCmeFile(List<IrSwap> swaps, Request request);
}
