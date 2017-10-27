package com.acuo.collateral.transform.services

import com.acuo.collateral.transform.TransformerContext
import com.acuo.collateral.transform.modules.TransformerModule
import com.acuo.common.model.product.SwapHelper
import com.acuo.common.model.trade.SwapTrade
import com.google.common.collect.ImmutableList
import spock.guice.UseModules
import spock.lang.Specification
import spock.lang.Subject

import javax.inject.Inject
import java.time.LocalDate

@UseModules(TransformerModule)
class ClarusTransformerSpec extends Specification {

    TransformerContext context = null

    @Subject
    @Inject
    ClarusTransformer transformer

    void setup(){
        context = new TransformerContext()
        context.setValueDate(LocalDate.now())
    }

    def "serialise swaps trades"() {
        given:
        SwapTrade trade = SwapHelper.createTrade();

        when:
        String csv = transformer.serialise(ImmutableList.of(trade), context)

        then:
        csv != null && csv != ""
    }
}
