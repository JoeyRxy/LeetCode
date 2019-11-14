package mine.basictest;

/**
 * 继承
 */
public class 父类 {

    private String str;

    public 父类(String str) {
        this.str = str;
    }

    public 父类() {
        str = "父类";
    }

    public void print() {
        System.out.println("我是" + str);
    }
}