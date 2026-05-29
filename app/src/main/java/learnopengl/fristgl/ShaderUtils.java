package learnopengl.fristgl;

import android.content.res.Resources;
import android.opengl.GLES30;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 加载着色器程序工具
 */
public class ShaderUtils {
    private static final String TAG = ShaderUtils.class.getSimpleName();

    public static int loadShader(int type, String source) {
        int shader = GLES30.glCreateShader(type);
        if (shader == 0) {
            Log.e(TAG, "glCreateShader failed, type: " + type);
            return 0;
        }
        GLES30.glShaderSource(shader, source);
        GLES30.glCompileShader(shader);

        int[] compiled = new int[1];
        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e(TAG, "Shader compilation failed, type: " + type);
            GLES30.glDeleteShader(shader);
            return 0;
        }
        return shader;
    }

    public static int createProgram(String vertexSource, String fragmentSource) {
        int vertexShader = loadShader(GLES30.GL_VERTEX_SHADER, vertexSource);
        if (vertexShader == 0) {
            Log.e(TAG, "Vertex shader load failed");
            return 0;
        }
        int fragmentShader = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentSource);
        if (fragmentShader == 0) {
            Log.e(TAG, "Fragment shader load failed");
            return 0;
        }

        int program = GLES30.glCreateProgram();
        if (program == 0) {
            Log.e(TAG, "glCreateProgram failed");
            return 0;
        }

        GLES30.glAttachShader(program, vertexShader);
        GLES30.glAttachShader(program, fragmentShader);
        GLES30.glLinkProgram(program);

        GLES30.glDetachShader(program, vertexShader);
        GLES30.glDetachShader(program, fragmentShader);
        GLES30.glDeleteShader(vertexShader);
        GLES30.glDeleteShader(fragmentShader);

        int[] linkStatus = new int[1];
        GLES30.glGetProgramiv(program, GLES30.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            Log.e(TAG, "Program link failed: " + GLES30.glGetProgramInfoLog(program));
            GLES30.glDeleteProgram(program);
            return 0;
        }
        Log.i(TAG, "linkStatus[0]: " + linkStatus[0]);
        return program;
    }

    public static String loadFromAsserts(String fileName, Resources resources) {
        if (fileName == null || resources == null) {
            Log.e(TAG, "loadFromAsserts: fileName or resources is null");
            return null;
        }
        String result = null;
        InputStream is = null;
        try {
            is = resources.getAssets().open(fileName);
            int length = is.available();
            byte[] data = new byte[length];
            int bytesRead = is.read(data);
            if (bytesRead != length) {
                Log.w(TAG, "loadFromAsserts: read " + bytesRead + " bytes, expected " + length);
            }
            result = new String(data, StandardCharsets.UTF_8);
            result = result.replace("\r\n", "\n");
        } catch (IOException e) {
            Log.e(TAG, "loadFromAsserts error: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignore) {
                }
            }
        }
        return result;
    }

    public static void deleteProgram(int program) {
        GLES30.glDeleteProgram(program);
    }
}
