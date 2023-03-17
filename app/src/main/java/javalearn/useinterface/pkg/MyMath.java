package javalearn.useinterface.pkg;

public class MyMath implements FunctionResult {
    private long mResult = 0;

    @Override
    public long getIntResult() {
        return mResult;
    }

    MyMath(int a, int b) {
        mResult = a + b;
    }
}
