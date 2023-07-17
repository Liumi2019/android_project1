package learnopengl.fristgl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.Log;

import androidx.annotation.NonNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * 第一个 OpenGL 例子
 */
public class FirstOpenGl {
    private static final String TAG = FirstOpenGl.class.getSimpleName();

    public static final int RETURN_OK = 0;

    public static final int RETURN_ERROR = 1;

    public static final String TYPE_NAME = "type";

    public static final int TYPE_NATIVE = 0;

    public static final int TYPE_JAVA = 1;

    private final int type;

    private final GLSurfaceView glSurfaceView;

    private final Context context;

    public FirstOpenGl(@NonNull Context context, int type) {
        Log.i(TAG, "FirstOpenGl");
        this.context = context;
        this.type = type;
        glSurfaceView = new GLSurfaceView(context);
        glSurfaceView.setEGLContextClientVersion(3);
        glSurfaceView.setRenderer(getRenderer());
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    /**
     * 返回 glSurfaceView
     *
     * @return glSurfaceView
     */
    public GLSurfaceView getGlSurfaceView() {
        return glSurfaceView;
    }

    /**
     * 获取 OpenGL 信息
     *
     * @return OpenGL 信息
     */
    public String getGlInfo() {
        String info = "";
        if (getGlVersion() == 196610) {
            info = "OpenGL ES 3.2";
        }
        return "OpenGL version: " + info;
    }

    /**
     * 创建渲染方式
     *
     * @return 返回 Java 或 Native 渲染实例
     */
    private GLSurfaceView.Renderer getRenderer() {
        Log.i(TAG, "getRenderer()");
        GLSurfaceView.Renderer renderer;
        if (type == TYPE_JAVA) {
            renderer = new JavaRenderer(context);
        } else {
            renderer = null;
        }
        return renderer;
    }

    private int getGlVersion() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ActivityManager activityManager = context.getSystemService(ActivityManager.class);
            ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
            return configurationInfo.reqGlEsVersion;
        }
        Log.e(TAG, "getGlVersion error");
        return -1;
    }

    /**
     * java 渲染
     *
     * 渲染器定义接口和回调函数
     */
    private static class JavaRenderer implements GLSurfaceView.Renderer {
        private final Context context;

        private int program;

        private int positionHandle;

        public JavaRenderer(@NonNull Context context) {
            this.context = context;
        }

        /**
         * Renderer 回调函数 onSurfaceCreated()
         *
         * @param gl the GL interface. Use <code>instanceof</code> to
         * test if the interface supports GL11 or higher interfaces.
         * @param config the EGLConfig of the created surface. Can be used
         * to create matching pbuffers.
         */
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            Log.i(TAG, "onSurfaceCreated()");
            String vertexSource = ShaderUtils.loadFromAsserts("vertex.vsh", context.getResources());
            String fragmentSource = ShaderUtils.loadFromAsserts("fragment.fsh", context.getResources());
            program = ShaderUtils.createProgram(vertexSource, fragmentSource);
            positionHandle = GLES30.glGetAttribLocation(program, "vPosition");

            // 使用黑色背景填充，刷新界面
            GLES30.glClearColor(0, 0, 0, 1);
        }

        /**
         * Renderer 回调函数 onSurfaceChanged()
         *
         * @param gl the GL interface. Use <code>instanceof</code> to
         * test if the interface supports GL11 or higher interfaces.
         * @param width 设备界面宽度
         * @param height 设备界面高度
         */
        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES30.glViewport(0, 0, width, height);
        }

        /**
         * Renderer 回调函数 onDrawFrame()
         *
         * @param gl the GL interface. Use <code>instanceof</code> to
         * test if the interface supports GL11 or higher interfaces.
         */
        @Override
        public void onDrawFrame(GL10 gl) {
            Log.i(TAG, "onDrawFrame()");
            int vertexCount = 3;
            float[] vertices = new float[]{
                    0.0f, 0.5f, 0f,
                    -0.5f, -0.5f, 0f,
                    0.5f, -0.5f, 0f
            };

            final int bufferSize = vertices.length * 4;
            ByteBuffer vbb = ByteBuffer.allocateDirect(bufferSize);
            vbb.order(ByteOrder.nativeOrder());
            FloatBuffer vertexBuffer = vbb.asFloatBuffer();
            vertexBuffer.put(vertices);
            vertexBuffer.position(0);

            GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
            GLES30.glUseProgram(program);

            final int strideSize = 12;
            GLES30.glVertexAttribPointer(positionHandle, vertexCount, GLES30.GL_FLOAT, false, strideSize, vertexBuffer);
            GLES30.glEnableVertexAttribArray(positionHandle);

            GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount);
        }
    }
}
