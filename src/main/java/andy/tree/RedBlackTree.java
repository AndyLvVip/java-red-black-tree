package andy.tree;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/1
 * @Description:
 */
public class RedBlackTree {

    private Node root = Node.END;

    public int size() {
        return root.getSize();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printWithIndex(String value, int depth) {
        for(int i = 0; i < depth; i++)
            System.out.print("\t\t\t");
        System.out.println(value);
    }
    private void printTree(Node node, int depth) {
        if(node == Node.END)
            printWithIndex("END", depth);
        else {
            printTree(node.getLeft(), depth + 1);
            if(node.isRed())
                printWithIndex("[" + node.getValue() + "(" + node.getSize() + ")" + "]", depth);
            else
                printWithIndex("" + node.getValue() + "(" + node.getSize() + ")", depth);
            printTree(node.getRight(), depth + 1);
        }
    }

    public Node rotLeft(Node node) {
        Node oriRight = node.getRight();
        node.setRight(oriRight.getLeft());
        oriRight.setLeft(node);
        return oriRight;
    }

    public Node rotRight(Node node) {
        Node oriLeft = node.getLeft();
        node.setLeft(oriLeft.getRight());
        oriLeft.setRight(node);
        return oriLeft;
    }

    public void insert(int value) {
        root = rbInsert(root, value, false);
        root.setRed(false);
        fixSize();
    }

    public void fixSize() {
        fixSize(root);
    }

    public void fixSize(Node node) {
        if(node == Node.END) {
            return;
        }
        fixSize(node.getLeft());
        fixSize(node.getRight());
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
    }

    private Node rbInsert(Node node, int value, boolean fromRight) {
        if(node == Node.END) {
            node = Node.newInstance(value);
        }else {
            if(node.getLeft().isRed() && node.getRight().isRed()) {
                node.setRed(true);
                node.getLeft().setRed(false);
                node.getRight().setRed(false);
            }
            if(value < node.getValue()) {
                node.setLeft(rbInsert(node.getLeft(), value, false));
                if(fromRight && node.isRed() && node.getLeft().isRed()) {
                    node = rotRight(node);
                }
                if(node.getLeft().isRed() && node.getLeft().getLeft().isRed()) {
                    node = rotRight(node);
                    node.setRed(false);
                    node.getRight().setRed(true);
                }
            }else {
                node.setRight(rbInsert(node.getRight(), value, true));
                if(!fromRight && node.isRed() && node.getRight().isRed()) {
                    node = rotLeft(node);
                }
                if(node.getRight().isRed() && node.getRight().getRight().isRed()) {
                    node = rotLeft(node);
                    node.setRed(false);
                    node.getLeft().setRed(true);
                }
            }
        }
        return node;
    }

    private static class Node {
        private boolean red;
        private int value;
        private Node left;
        private Node right;
        private int size;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        private static final Node END = new Node();

        public static Node newInstance(int value) {
            Node node = new Node();
            node.setRed(true);
            node.setLeft(END);
            node.setRight(END);
            node.setValue(value);
            node.setSize(1);
            return node;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
