package andy.tree;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/1
 * @Description:
 */
public class TestRedBlackTree {

    @Test
    public void printEmptyTree() {
        new RedBlackTree().printTree();
    }

    @Test
    public void printNotEmptyTree() {
        RedBlackTree tree = new RedBlackTree();
        Set<Integer> nums = new HashSet<>();
        while(nums.size() < 10)
            nums.add(new Random().nextInt(100));
        nums.forEach(i -> {
            tree.insert(i);
            System.out.println("--------------begin" + i + "-------------------");
            tree.printTree();
            System.out.println("--------------end" + i + "-------------------");
        });
    }
}
