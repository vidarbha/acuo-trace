package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.collateral.transform.trace.utils.SwapHelper;
import com.acuo.common.model.trade.SwapTrade;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TransformerTest {

    private TransformerContext context = null;

    @Before
    public void setup() {
        context = new TransformerContext();
        context.setValueDate(LocalDate.now());
    }

    @Test
    public void testSerialiseSwapsWithClarus() throws Exception {
        Transformer<SwapTrade> transformer = new ClarusTransformer(new Mapper());

        SwapTrade trade = SwapHelper.createTrade();

        String csv = transformer.serialise(ImmutableList.of(trade), context);

        assertThat(csv).isNotEmpty();
    }

    @Test
    public void testSerialiseSwapsWithMarkit() throws Exception {
        Transformer<SwapTrade> transformer = new MarkitTransformer(new Mapper());

        SwapTrade trade = SwapHelper.createTrade();

        String xml = transformer.serialise(ImmutableList.of(trade), context);

        assertThat(xml).isNotEmpty();
    }
}