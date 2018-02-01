package andy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/1
 * @Description:
 */
public class PayInvocationHandler implements InvocationHandler {

    private Pay pay;

    public PayInvocationHandler(Pay pay) {
        this.pay = pay;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before paying...");
        method.invoke(pay, args);
        System.out.println("after paying...");
        return null;
    }
}
