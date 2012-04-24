package org.example.bean.internal;

import org.example.bean.ExampleBean;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExampleBeanImplTest {

    @Test
    public void testBeanIsABean() {
        ExampleBean aBean = new ExampleBeanImpl();
        assertTrue(aBean.isABean());
    }
}
