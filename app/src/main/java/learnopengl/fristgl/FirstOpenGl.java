package learnopengl.fristgl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.text.TextUtils;
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

    private static final int MIN_VERTEX_COUNt = 6;

    private static final int FLOAT_BIT = 4;

    // 顶点总数
    private static int vertexCount = 2;

    // 每个顶点的数据数目
    private static int countPerVertex = 3;

    // 步长，如数组索引[0~2，3~7]为位置数据，4、5为其他数据，
    // 则步长为3（位置） + 1（其他）： 4 * 类型数
    private static int strideSize = 4 * FLOAT_BIT; // vertexCount * FLOAT_BIT

    private static float[] position = null;

    private static float[] color = null;

    private final int type;

    private GLSurfaceView glSurfaceView;

    private GLSurfaceView.Renderer renderer = null;

    private final Context context;

    public FirstOpenGl(@NonNull Context context, int type) {
        Log.i(TAG, "FirstOpenGl");
        this.context = context;
        this.type = type;
        initGlSurfaceView();
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
     * 返回 glSurfaceView
     *
     * @return glSurfaceView
     */
    public GLSurfaceView getGlSurfaceView() {
        return glSurfaceView;
    }

    private void initGlSurfaceView() {
        glSurfaceView = new GLSurfaceView(context);
        glSurfaceView.setEGLContextClientVersion(3);

        initRenderer();
        if (renderer == null) {
            Log.e(TAG, "renderer is null");
            return;
        }
        glSurfaceView.setRenderer(renderer);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    /**
     * 创建渲染方式
     */
    private void initRenderer() {
        Log.i(TAG, "getRenderer()");
        if (type == TYPE_JAVA) {
            renderer = new JavaRenderer(context);
        }
    }

    /**
     * 设置位置信息
     *
     * @param position_    位置信息
     * @param vertexCount_ 每个顶点数据个数
     * @return true 设置成功，false 设置失败，采用默认值
     */
    public boolean setPosition(float[] position_, int vertexCount_) {
        if (position == null || position.length < MIN_VERTEX_COUNt || vertexCount == 0) {
            Log.e(TAG, "set position error!");
            return false;
        }
        if (position.length / vertexCount < 3) {
            Log.e(TAG, "at least 3 points!");
            return false;
        }
        position = position_;
        vertexCount = vertexCount_;
        strideSize = vertexCount * FLOAT_BIT;
        return true;
    }

    /**
     * java 渲染
     * <p>
     * 渲染器定义接口和回调函数
     */
    private static class JavaRenderer implements GLSurfaceView.Renderer {
        private final Context context;

        private String vertexSource = "";

        private String fragmentSource = "";

        private int program = 0;

        /**
         * 顶点对象句柄，用户处理顶点着色器程序的顶点数据
         */
        private int vertexHandle = 0;

        private int pointSizeHandle = 0;

        /**
         * 片段着色器对象句柄，用户处理片段着色器程序
         */
        private int fragmentHandle = 0;

        public JavaRenderer(@NonNull Context context) {
            this.context = context;
        }

        private void getPosition() {
            // 位置数组
            position = new float[]{
                // 位置 * 3----, // 其他数据 * 1
                0.0f, 0.5f, 0f, 10.0f,
                -0.5f, -0.5f, 0f, 10.0f,
                0.5f, -0.5f, 0f, 20.0f,
                0.0f, -1.0f, 0f, 20.0f
            };

            // 每个顶点的数据数目
            countPerVertex = 3;

            // 顶点总数
            vertexCount = 4;

            // 计算每个顶点的步长，如数组索引[0~2，3~7]为位置数据，4、5为其他数据，
            // 则步长为3（位置） + 1（其他） 4 * 类型数
            int otherDataCount = 1;
            strideSize = (countPerVertex + otherDataCount) * FLOAT_BIT;
        }

        private void getColor() {
            // color rgba 四维向量
            if (color == null || color.length < MIN_VERTEX_COUNt) {
                color = new float[]{0.0f, 1.0f, 1.0f, 1.0f};
            }
        }

        private void createProgram() {
            try {
                vertexSource = ShaderUtils.loadFromAsserts("vertex.vsh", context.getResources());
            } catch (RuntimeException ignore) {
                Log.e(TAG, "load vertexSource error!");
            }
            try {
                fragmentSource = ShaderUtils.loadFromAsserts("fragment.fsh", context.getResources());
            } catch (RuntimeException ignore) {
                Log.e(TAG, "load vertexSource error!");
            }
            if (!TextUtils.isEmpty(vertexSource) && !TextUtils.isEmpty(fragmentSource)) {
                program = ShaderUtils.createProgram(vertexSource, fragmentSource);
            } else {
                program = 0;
                Log.e(TAG, "program is 0");
            }
        }

        private void initPositionHandle() {
            // 获取 gl 程序中的顶点对象句柄，使用 glVertexAttribPointer() 拷贝数据到 GL 着色器程序中
            vertexHandle = GLES30.glGetAttribLocation(program, "vPosition");
            Log.i(TAG, "vertexHandle: " + vertexHandle);
            pointSizeHandle = GLES30.glGetAttribLocation(program, "PointSize");
            Log.i(TAG, "vertexHandle: " + pointSizeHandle);
        }

        private void initColorHandle() {
            // 获取 gl 程序中的顶点对象句柄，使用 glVertexAttribPointer() 拷贝数据到 GL 着色器程序中
            fragmentHandle = GLES30.glGetUniformLocation(program, "Scolor");
            Log.i(TAG, "fragmentHandle: " + fragmentHandle);
        }

        /**
         * 将顶点数据（float 数组），转为 FloatBuffer，拷贝到顶点着色器程序中
         * java 运行于虚拟机中，需要转为 native 内存才能拷贝到着色器程序中
         *
         * @return FloatBuffer
         */
        private FloatBuffer getVertexBuffer() {
            // 获取顶点字节数
            final int bufferSize = position.length * FLOAT_BIT;
            ByteBuffer vbb = ByteBuffer.allocateDirect(bufferSize);
            vbb.order(ByteOrder.nativeOrder());
            FloatBuffer vertexBuffer = vbb.asFloatBuffer();
            vertexBuffer.put(position);
            vertexBuffer.position(0);
            return vertexBuffer;
        }

        private FloatBuffer getFragmentBuffer() {
            // 获取顶点字节数
            final int bufferSize = color.length * FLOAT_BIT;
            ByteBuffer vbb = ByteBuffer.allocateDirect(bufferSize);
            vbb.order(ByteOrder.nativeOrder());
            FloatBuffer vertexBuffer = vbb.asFloatBuffer();
            vertexBuffer.put(color);
            vertexBuffer.position(0);
            return vertexBuffer;
        }


        /**
         * Renderer 回调函数 onSurfaceCreated()
         *
         * @param gl     the GL interface. Use <code>instanceof</code> to
         *               test if the interface supports GL11 or higher interfaces.
         * @param config the EGLConfig of the created surface. Can be used
         *               to create matching buffers.
         */
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            Log.i(TAG, "onSurfaceCreated()");

            // 创建 gl程序对像
            createProgram();

            initPositionHandle();
            initColorHandle();

            getPosition();
            getColor();

            // 使用黑色背景填充，刷新界面
            GLES30.glClearColor(0.5f, 0.0f, 0.0f, 1.0f);
        }

        /**
         * Renderer 回调函数 onSurfaceChanged()
         *
         * @param gl     the GL interface. Use <code>instanceof</code> to
         *               test if the interface supports GL11 or higher interfaces.
         * @param width  设备界面宽度
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
         *           test if the interface supports GL11 or higher interfaces.
         */
        @Override
        public void onDrawFrame(GL10 gl) {
            Log.i(TAG, "onDrawFrame()");

            FloatBuffer vertexBuffer = getVertexBuffer();
            GLES30.glVertexAttribPointer(vertexHandle, countPerVertex, GLES30.GL_FLOAT, false, strideSize, vertexBuffer);
            GLES30.glEnableVertexAttribArray(vertexHandle);

            vertexBuffer.position(3);
            GLES30.glVertexAttribPointer(pointSizeHandle, 1, GLES30.GL_FLOAT, false, strideSize, vertexBuffer);
            GLES30.glEnableVertexAttribArray(pointSizeHandle);

            // GL 设置颜色
            GLES30.glUniform4fv(fragmentHandle, 1, getFragmentBuffer());

            GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

            // 使用程序
            GLES30.glUseProgram(program);
            // 绘制新图像
            // GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount);

            // 绘制点
            GLES30.glDrawArrays(GLES30.GL_POINTS, 0, vertexCount);
        }
    }
}
