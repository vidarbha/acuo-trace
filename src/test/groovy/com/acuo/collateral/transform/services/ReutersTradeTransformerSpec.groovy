package com.acuo.collateral.transform.services

import com.acuo.collateral.transform.TransformerContext
import com.acuo.collateral.transform.TransformerOutput
import com.acuo.collateral.transform.modules.TransformerModule
import com.acuo.common.model.product.SwapHelper
import com.acuo.common.model.results.TradeValuation
import com.acuo.common.util.ResourceFile
import org.junit.Rule
import spock.guice.UseModules
import spock.lang.Specification
import spock.lang.Subject

import javax.inject.Inject
import java.time.LocalDate

import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson
import static org.junit.Assert.assertThat

@UseModules(TransformerModule)
class ReutersTradeTransformerSpec extends Specification {

    @Rule
    ResourceFile response = new ResourceFile("/reuters/trade/response.json")

    TransformerContext context = null

    @Subject
    @Inject
    ReutersTradeTransformer transformer

    void setup(){
        context = new TransformerContext()
        context.setValueDate(LocalDate.now())
    }

    def "Serialise one trade"() {
        given:
        def trade = SwapHelper.createTrade()

        when:
        String json = transformer.serialise([trade], context)

        then:
        json != null

        and:
        assertThat json, isJson()
    }

    def "Deserialize one asset"() {
        when:
        TransformerOutput<TradeValuation> output = transformer.deserialiseToList(response.getContent())

        then:
        with(output.results()) {
            it.size() > 0
            it.get(0) != null
        }
    }
}
