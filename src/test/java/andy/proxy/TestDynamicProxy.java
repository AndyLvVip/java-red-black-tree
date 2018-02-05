package andy.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public boolean foo(char c) {
        System.out.print(c);
        return true;
    }

    private static class Foo {
        private String name;

        public Foo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null)
                return false;
            if(obj instanceof Foo)
                return Objects.equals(this.name, ((Foo) obj).getName());
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    @Test
    public void testEquals() {
        Set<Foo> foos = new HashSet<>();
        foos.add(new Foo("hello"));
        foos.add(new Foo(new String("hello")));
        System.out.println(foos.size());
    }

    @Test
    public void length() {
        Set set = new HashSet();
        set.add("A1");
        set.add("A2");

        set.add(new String("A1"));
        set.add(new String("A2"));
        System.out.println("A1" == new String("A1"));
        System.out.println("A1".equals(new String("A1")));
        System.out.println(set.size());
    }

    @Test
    public void doFoo() {
        int i = 0;

        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }

    public void changeStr(String str){
        str = "Welcome To Midea";
    }

    @Test
    public void change() {
        String str = "Welcome";
        changeStr(str);
        System.out.println(str);
    }
}
