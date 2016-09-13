package com.acuo.collateral.transform.trace;

import com.acuo.collateral.transform.trace.lookup.ConfigFileLookupProvider;
import com.tracegroup.transformer.dad.TBeanConfiguration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ConfigFileLookupProviderTest {

    ConfigFileLookupProvider provider = new ConfigFileLookupProvider();

    @Mock
    TBeanConfiguration configuration;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore
    public void testGetHolidayCode() {
        when(configuration.getPropertyValue("ConfigFile")).thenReturn("/MarkitHolidayCodes.ini");
        assertThat(provider.createLookup().containsKey("GBLO")).isTrue();
    }
}
