package use.refrence.demo;

/**
 * 测试引用
 */
public class demo1 {
    private final int[] updateA;

    private final demo1_in demo1_in1;

    public demo1() {
        updateA = new int[1];
        demo1_in1 = new demo1_in(updateA);
    }

    private void AddUpdateA(int[] _a) {
        _a[0]++;
    }

    public void printA() {
        demo1_in1.printa();
        System.out.println("liu, _a = " + updateA[0]);
        AddUpdateA(updateA);
        demo1_in1.printa();
        System.out.println("liu, _a = " + updateA[0]);
    }
}
