package com.acuo.collateral.transform.trace;

import com.acuo.collateral.transform.trace.lookup.ConfigFileLookupProvider;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigFileLookupProviderTest {

    ConfigFileLookupProvider provider = new ConfigFileLookupProvider();

    @Test
    public void testGetHolidayCode() {
        assertThat(provider.createLookup().containsKey("GBLO")).isTrue();
    }
}
