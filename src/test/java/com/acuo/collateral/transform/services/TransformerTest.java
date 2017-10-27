package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.margin.AgreeTransformer;
import com.acuo.collateral.transform.margin.CancelTransformer;
import com.acuo.collateral.transform.margin.CreateTransformer;
import com.acuo.collateral.transform.margin.DeliveryMapTransformer;
import com.acuo.collateral.transform.margin.DisputeTransformer;
import com.acuo.collateral.transform.margin.MarginSphereTransformer;
import com.acuo.collateral.transform.margin.PledgeCreateTransformer;
import com.acuo.collateral.transform.margin.StatementItemTransformer;
import com.acuo.collateral.transform.modules.TransformerModule;
import com.acuo.common.model.assets.Assets;
import com.acuo.common.model.margin.MarginCall;
import com.acuo.common.model.margin.Recall;
import com.acuo.common.model.results.TradeValuation;
import com.acuo.common.model.trade.SwapTrade;
import com.acuo.common.util.GuiceJUnitRunner;
import com.acuo.common.util.ResourceFile;
import com.google.common.collect.ImmutableList;
import com.google.inject.Injector;
import com.opengamma.strata.basics.currency.Currency;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({
        TransformerModule.class
})
public class TransformerTest {

    private TransformerContext context = null;

    @Inject
    private Injector injector = null;

    @Rule
    public ResourceFile received = new ResourceFile("/call/Received_Call.xml");

    @Rule
    public ResourceFile responsjson = new ResourceFile("/reuters/asset/response.json");

    @Rule
    public ResourceFile statementItem = new ResourceFile("/mockmc.csv");

    @Rule
    public ResourceFile oneTradeFile = new ResourceFile("/portfolio/OneIRS.xlsx");

    @Rule
    public ResourceFile tradePortfolio = new ResourceFile("/portfolio/TradePortfolio-OW-650.xlsx");

    @Before
    public void setup() {
        context = new TransformerContext();
        context.setValueDate(LocalDate.now());
    }


    @Test
    public void testSerialiseSwapsWithMarginsphere() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(MarginSphereTransformer.class);

        TransformerOutput<MarginCall> output = transformer.deserialiseToList(received.getContent());
        List<MarginCall> marginCalls = output.results();

        assertThat(marginCalls).isNotEmpty();
    }

    @Test
    @Ignore
    public void testStatementItemImport() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(StatementItemTransformer.class);
        String content = statementItem.getContent();
        if (content != null) {
            content = content.replace("\n", "\r\n");
        }
        TransformerOutput<MarginCall> output = transformer.deserialiseToList(content);
        List<MarginCall> marginCalls = output.results();
        assertThat(marginCalls).isNotEmpty();
    }

    @Test
    public void testDisputeRequest() throws Exception {
        DisputeTransformer<MarginCall> transformer = injector.getInstance(DisputeTransformer.class);
        MarginCall marginCall = new MarginCall();
        marginCall.setId("cantortest");
        List<MarginCall> marginCalls = new ArrayList<>();
        marginCalls.add(marginCall);
        String reqeset = transformer.serialise(marginCalls, null);
        assertThat(reqeset).isNotEmpty();
    }


    @Test
    public void testTradePortfolio() throws Exception {
        Transformer<String, SwapTrade> transformer = injector.getInstance(PortfolioImportTransformer.class);
        TransformerOutput<SwapTrade> output = transformer.deserialise(IOUtils.toByteArray(tradePortfolio.getInputStream()));
        List<SwapTrade> trades = output.results();
        assertThat(trades).isNotEmpty();
    }

    @Test
    public void testOneTradePortfolio() throws Exception {
        Transformer<String, SwapTrade> transformer = injector.getInstance(PortfolioImportTransformer.class);
        TransformerOutput<SwapTrade> output = transformer.deserialise(IOUtils.toByteArray(oneTradeFile.getInputStream()));
        List<SwapTrade> trades = output.results();
        assertThat(trades).isNotEmpty();
    }

    @Test
    public void testNPV() throws Exception {
        Transformer<String, TradeValuation> transformer = injector.getInstance(TradeValuationTransformer.class);
        TransformerOutput<TradeValuation> output = transformer.deserialise(IOUtils.toByteArray(tradePortfolio.getInputStream()));
        List<TradeValuation> tradeValuations = output.results();
        assertThat(tradeValuations).isNotEmpty();
    }

    @Test
    public void testDeliveryMap() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(DeliveryMapTransformer.class);
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testssss");
        marginCall.setModifyDate(LocalDateTime.now());
        final String result = transformer.serialise(marginCall, null);
        assertThat(result).isNotNull();
    }

    @Test
    public void testSerialiseSettlementDate() throws Exception {
        Transformer<Assets, String> transformer = injector.getInstance(SettlementDateTransformer.class);

        Assets assets = new Assets();
        assets.setAssetId("1231");
        assets.setAvailableQuantities(11);
        assets.setCurrency(Currency.USD);
        assets.setFitchRating("1");

        String json = transformer.serialise(ImmutableList.of(assets), context);
        assertThat(json).isNotEmpty();
    }

    @Test
    public void testAgree() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(AgreeTransformer.class);
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        final String result = transformer.serialise(marginCall, null);
        assertThat(result).isNotNull();
    }

    @Test
    public void testCreate() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(CreateTransformer.class);
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        marginCall.setRoundingAmount(111d);
        final String result = transformer.serialise(marginCall, null);
        assertThat(result).isNotNull();
    }

    @Test
    public void testCancel() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(CancelTransformer.class);
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        marginCall.setRoundingAmount(111d);
        final String result = transformer.serialise(marginCall, null);
        assertThat(result).isNotNull();
    }

    @Test
    public void testPledgeCreate() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(PledgeCreateTransformer.class);
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        marginCall.setVersion(1);
        Recall recall = new Recall();
        recall.setRecallAmpId("ampidrecall");
        recall.setRecallIsAccepted(true);
        Set<Recall> recallSet = new HashSet<Recall>();
        recallSet.add(recall);
        marginCall.setRecalls(recallSet);
        String xml = transformer.serialise(marginCall, null);
        Assert.assertTrue(xml.contains("recallItem"));
    }

    @Test
    public void testRejectRecall() throws Exception {
        Transformer<MarginCall, MarginCall> transformer = injector.getInstance(PledgeCreateTransformer.class);
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        marginCall.setVersion(1);
        Recall recall = new Recall();
        recall.setRecallAmpId("ampidrecall");
        recall.setRecallIsAccepted(false);
        recall.setRejectReasonCode(999);
        recall.setRejectComment("cantortest");
        Set<Recall> recallSet = new HashSet<Recall>();
        recallSet.add(recall);
        marginCall.setRecalls(recallSet);
        String xml = transformer.serialise(marginCall, null);
        Assert.assertTrue(xml.contains("cantortest"));
    }
}