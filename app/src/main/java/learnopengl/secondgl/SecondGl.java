package learnopengl.secondgl;

import android.view.Surface;

/**
 * 第二个 OpenGL 程序
 * 对接 C++ 接口
 */
public class SecondGl {
    private static final String TAG = SecondGl.class.getSimpleName();

    public SecondGl() {
    }

    public void setSurface(Surface surface) {
        nativeSetSurface(surface);
    }

    private native int nativeSetSurface(Object surface);
}
