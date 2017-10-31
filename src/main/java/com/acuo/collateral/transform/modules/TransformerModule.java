package com.acuo.collateral.transform.modules;

import com.acuo.collateral.transform.TransformFrom;
import com.acuo.collateral.transform.TransformTo;
import com.acuo.collateral.transform.margin.AgreeTransformer;
import com.acuo.collateral.transform.margin.CancelTransformer;
import com.acuo.collateral.transform.margin.CreateTransformer;
import com.acuo.collateral.transform.margin.DeliveryMapTransformer;
import com.acuo.collateral.transform.margin.DisputeTransformer;
import com.acuo.collateral.transform.margin.MarginCallTransformer;
import com.acuo.collateral.transform.margin.PledgeAcceptTransformer;
import com.acuo.collateral.transform.margin.PledgeCreateTransformer;
import com.acuo.collateral.transform.margin.StatementItemTransformer;
import com.acuo.collateral.transform.trace.transformer_agreement.MarginAgreement;
import com.acuo.collateral.transform.trace.transformer_clarus.Clarus;
import com.acuo.collateral.transform.trace.transformer_cme.Cme;
import com.acuo.collateral.transform.trace.transformer_margin.MSMapper;
import com.acuo.collateral.transform.trace.transformer_markit.Markit;
import com.acuo.collateral.transform.trace.transformer_portfolio.Portfolio;
import com.acuo.collateral.transform.trace.transformer_reuters.Reuters;
import com.acuo.common.model.margin.MarginCall;
import com.acuo.marginsphere.Response;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransformerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MSMapper.class);
        bind(Clarus.class);
        bind(Markit.class);
        bind(Reuters.class);
        bind(MarginAgreement.class);
        bind(Cme.class);
        bind(Portfolio.class);

        bind(new TypeLiteral<TransformFrom<Response>>(){})
                .annotatedWith(Names.named("marginSphere"))
                .to(MarginCallTransformer.class);

        bind(new TypeLiteral<TransformFrom<Response>>(){})
                .annotatedWith(Names.named("statementItem"))
                .to(StatementItemTransformer.class);

        bind(new TypeLiteral<TransformTo<MarginCall>>(){})
                .annotatedWith(Names.named("dispute"))
                .to(DisputeTransformer.class);

        bind(new TypeLiteral<TransformTo<MarginCall>>(){})
                .annotatedWith(Names.named("pledgeCreate"))
                .to(PledgeCreateTransformer.class);

        bind(new TypeLiteral<TransformTo<MarginCall>>(){})
                .annotatedWith(Names.named("pledgeAccept"))
                .to(PledgeAcceptTransformer.class);

        bind(new TypeLiteral<TransformTo<MarginCall>>(){})
                .annotatedWith(Names.named("deliveryMap"))
                .to(DeliveryMapTransformer.class);

        bind(new TypeLiteral<TransformTo<MarginCall>>(){})
                .annotatedWith(Names.named("create"))
                .to(CreateTransformer.class);

        bind(new TypeLiteral<TransformTo<MarginCall>>(){})
                .annotatedWith(Names.named("agree"))
                .to(AgreeTransformer.class);

        bind(new TypeLiteral<TransformTo<MarginCall>>(){})
                .annotatedWith(Names.named("cancel"))
                .to(CancelTransformer.class);
    }
}
