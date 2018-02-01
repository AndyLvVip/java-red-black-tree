package andy.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/1
 * @Description:
 */
public class TestDynamicProxy {

    @Test
    public void test() {
        PayInvocationHandler handler = new PayInvocationHandler(new PayImpl());
        Pay pay = (Pay) Proxy.newProxyInstance(Pay.class.getClassLoader(), new Class[]{Pay.class}, handler);
        pay.pay(1.23);
    }
}
