package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.margin.*;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.common.model.assets.Assets;
import com.acuo.common.model.margin.MarginCall;
import com.acuo.common.model.margin.Recall;
import com.acuo.common.model.product.SwapHelper;
import com.acuo.common.model.results.AssetValuation;
import com.acuo.common.model.results.TradeValuation;
import com.acuo.common.model.trade.SwapTrade;
import com.acuo.common.util.ResourceFile;
import com.google.common.collect.ImmutableList;
import com.opengamma.strata.basics.currency.Currency;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TransformerTest {

    private TransformerContext context = null;

    @Rule
    public ResourceFile received = new ResourceFile("/call/Received_Call.xml");

    @Rule
    public ResourceFile responsjson = new ResourceFile("/reuters/response.json");

    @Rule
    public ResourceFile statementItem = new ResourceFile("/mockmc.csv");

    @Rule
    public ResourceFile oneTradeFile = new ResourceFile("/portfolio/OneIRS.xlsx");

    @Rule
    public ResourceFile tradePortfolio = new ResourceFile("/portfolio/TradePortfolio_OW-742.xlsx");

    @Before
    public void setup() {
        context = new TransformerContext();
        context.setValueDate(LocalDate.now());
    }

    @Test
    public void testSerialiseSwapsWithClarus() throws Exception {
        Transformer<SwapTrade> transformer = new ClarusTransformer<>(new Mapper());

        SwapTrade trade = SwapHelper.createTrade();

        String csv = transformer.serialise(ImmutableList.of(trade), context);

        assertThat(csv).isNotEmpty();
    }

    @Test
    public void testSerialiseSwapsWithMarkit() throws Exception {
        Transformer<SwapTrade> transformer = new MarkitTransformer<>(new Mapper());

        SwapTrade trade = SwapHelper.createTrade();

        String xml = transformer.serialise(ImmutableList.of(trade), context);

        assertThat(xml).isNotEmpty();
    }

    @Test
    public void testSerialiseAssetsWithReuters() throws Exception {
        Transformer<Assets> transformer = new ReutersTransformer<>();

        Assets assets = new Assets();
        assets.setAssetId("1231");
        assets.setAvailableQuantities(11);
        assets.setCurrency(Currency.USD);
        assets.setFitchRating("1");

        String json = transformer.serialise(ImmutableList.of(assets), context);

        assertThat(json).isNotEmpty();
    }

    @Test
    public void testSerialiseAssetsFromReuters() throws Exception {
        Transformer<AssetValuation> transformer = new ReutersTransformer<>();

        List<AssetValuation> assetsList = transformer.deserialiseToList(responsjson.getContent());

        Assert.assertTrue(assetsList.size() > 0);
        AssetValuation valuation = assetsList.get(0);
        assertThat(valuation).isNotNull();
    }

    @Test
    public void testSerialiseSwapsWithMarginsphere() throws Exception {
        Transformer<MarginCall> transformer = new MarginSphereTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());

        List<MarginCall> marginCalls = transformer.deserialiseToList(received.getContent());

        assertThat(marginCalls).isNotEmpty();
    }

    @Test
    @Ignore
    public void testStatementItemImport() throws Exception {
        Transformer<MarginCall> transformer = new StatementItemTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
        String content = statementItem.getContent();
        if (content != null) {
            content = content.replace("\n", "\r\n");
        }
        List<MarginCall> marginCalls = transformer.deserialiseToList(content);
        assertThat(marginCalls).isNotEmpty();
    }

    @Test
    public void testDisputeRequest() throws Exception {
        DisputeTransformer<MarginCall> transformer = new DisputeTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
        MarginCall marginCall = new MarginCall();
        marginCall.setId("cantortest");
        List<MarginCall> marginCalls = new ArrayList<>();
        marginCalls.add(marginCall);
        String reqeset = transformer.serialise(marginCalls, null);
        assertThat(reqeset).isNotEmpty();
    }


    @Test
    public void testTradePortfolio() throws Exception {
        Transformer<SwapTrade> transformer = new PortfolioImportTransformer<>(new Mapper());
        List<SwapTrade> trades = transformer.deserialise(IOUtils.toByteArray(tradePortfolio.getInputStream()));
        assertThat(trades).isNotEmpty();
    }

    @Test
    public void testOneTradePortfolio() throws Exception {
        Transformer<SwapTrade> transformer = new PortfolioImportTransformer<>(new Mapper());
        List<SwapTrade> trades = transformer.deserialise(IOUtils.toByteArray(oneTradeFile.getInputStream()));
        assertThat(trades).isNotEmpty();
    }

    @Test
    public void testNPV() throws Exception {
        Transformer<TradeValuation> transformer = new TradeValuationTransformer<>(new Mapper());
        List<TradeValuation> tradeValuations = transformer.deserialise(IOUtils.toByteArray(tradePortfolio.getInputStream()));
        assertThat(tradeValuations).isNotEmpty();
    }

    @Test
    public void testDeliveryMap() throws Exception
    {
        Transformer<MarginCall> transformer = new DeliveryMapTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testssss");
        marginCall.setModifyDate(LocalDateTime.now());
        log.info(transformer.serialise(marginCall, null));
    }

    @Test
    public void testSerialiseSettlementDate() throws Exception {
        Transformer<Assets> transformer = new SettlementDateTransformer<>();

        Assets assets = new Assets();
        assets.setAssetId("1231");
        assets.setAvailableQuantities(11);
        assets.setCurrency(Currency.USD);
        assets.setFitchRating("1");

        String json = transformer.serialise(ImmutableList.of(assets), context);
        log.info(json);
        assertThat(json).isNotEmpty();
    }

    @Test
    public void testAgree() throws Exception
    {
        Transformer<MarginCall> transformer = new AgreeTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        log.info(transformer.serialise(marginCall, null));
    }

    @Test
    public void testCreate() throws Exception
    {
        Transformer<MarginCall> transformer = new CreateTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        marginCall.setRoundingAmount(111d);
        log.info(transformer.serialise(marginCall, null));
    }

    @Test
    public void testCancel() throws Exception
    {
        Transformer<MarginCall> transformer = new CancelTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        marginCall.setRoundingAmount(111d);
        log.info(transformer.serialise(marginCall, null));
    }

    @Test
    public void testPledgeCreate() throws Exception
    {
        Transformer<MarginCall> transformer = new PledgeCreateTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
        MarginCall marginCall = new MarginCall();
        marginCall.setAmpId("testss");
        marginCall.setVersion(1);
        Recall recall = new Recall();
        recall.setRecallAmpId("ampidrecall");
        recall.setRecallIsAccepted(true);
        Set<Recall> recallSet = new HashSet<Recall>();
        recallSet.add(recall);
        marginCall.setRecalls(recallSet);
        String xml = transformer.serialise(marginCall,null);
        Assert.assertTrue(xml.contains("recallItem"));
    }

    @Test
    public void testRejectRecall() throws Exception
    {
        Transformer<MarginCall> transformer = new PledgeCreateTransformer<>(new com.acuo.collateral.transform.trace.transformer_margin.MarginCall());
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
        String xml = transformer.serialise(marginCall,null);
        log.info(xml);
        Assert.assertTrue(xml.contains("cantortest"));
    }
}