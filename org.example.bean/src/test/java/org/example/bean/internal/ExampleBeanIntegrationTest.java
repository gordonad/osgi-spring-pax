package org.example.bean.internal;

import org.example.bean.ExampleBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Integration test the bundle locally (outside of OSGi).
 * Use AbstractOsgiTests and a separate integration test project
 * for testing inside of OSGi.
 */
@ContextConfiguration("classpath:/META-INF/spring/bundle-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleBeanIntegrationTest {
    @Autowired
    private ExampleBean myBean;

    @Test
    public void testBeanIsABean() {
        assertTrue(this.myBean.isABean());
    }
}
