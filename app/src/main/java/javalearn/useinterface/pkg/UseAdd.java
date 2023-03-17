package javalearn.useinterface.pkg;

public class UseAdd implements FunctionResult {
    private long mResult = 0;

    @Override
    public long getIntResult() {
        return mResult;
    }

    UseAdd(int a, int b) {
        mResult = a + b;
    }
}
