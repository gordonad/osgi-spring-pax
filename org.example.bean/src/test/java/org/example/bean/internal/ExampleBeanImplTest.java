package org.example.bean.internal;

import org.example.bean.ExampleBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

public class ExampleBeanImplTest {

    @Test
    public void testBeanIsABean() {
        ExampleBean aBean = new ExampleBeanImpl();
        assertTrue(aBean.isABean());
    }
}
