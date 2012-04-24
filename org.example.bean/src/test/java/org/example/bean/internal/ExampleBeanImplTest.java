package org.example.bean.internal;

import junit.framework.TestCase;
import org.example.bean.ExampleBean;

public class ExampleBeanImplTest extends TestCase {
    public void testBeanIsABean() {
        ExampleBean aBean = new ExampleBeanImpl();
        assertTrue(aBean.isABean());
    }
}
