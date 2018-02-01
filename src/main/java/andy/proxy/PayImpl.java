package andy.proxy;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/1
 * @Description:
 */
public class PayImpl implements Pay {
    @Override
    public void pay(double amount) {
        System.out.println("paying..." + amount);
    }
}
