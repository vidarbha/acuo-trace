package com.acuo.collateral.transform.utils

import com.acuo.collateral.transform.TransformerOutput
import spock.lang.Specification

class OutputBuilderTest extends Specification {

    def "BuildResults"() {
        given:
        Object[] results = [1, 2, "3"]
        Object[] errors = null

        when:
        TransformerOutput<Integer> output = OutputBuilder.of(results, errors).build()

        then:
        output != null
        output.results() != null
    }
}
