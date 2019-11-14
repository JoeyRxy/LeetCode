package mine.basictest;

/**
 * 子类
 */
public class 子类 extends 父类 {
    private String pStr;

    public 子类(String pStr) {
        // 默认调用super();要求父类有无参构造的显式声明
        // super(pStr);
        this.pStr = pStr;

    }

    public static void main(String[] args) {
        父类 a = new 子类("子类");
        a.print();
        if (a instanceof 子类) {
            子类 son = (子类) a;
            son.printSon();
        }
    }

    // @Override
    public void printSon() {
        System.out.println("我是" + pStr);
    }
}