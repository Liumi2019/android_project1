package learnopengl.fristgl;

import android.content.res.Resources;
import android.opengl.GLES30;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ShaderUtils {
    private static final String TAG = ShaderUtils.class.getSimpleName();

    public static int loadShader(int type, String source) {
        int shader = GLES30.glCreateShader(type);
        if (shader == 0) {
            Log.e(TAG, "shader: " + shader);
            return 0;
        }
        Log.i(TAG, "shader: " + shader);
        GLES30.glShaderSource(shader, source);
        GLES30.glCompileShader(shader);
        int[] compiled = new int[1];
        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e(TAG, "compiled[0]: " + compiled[0]);
            return 0;
        }
        Log.i(TAG, "compiled[0]: " + compiled[0]);
        return shader;
    }

    public static int createProgram(String vertexSource, String fragmentSource) {
        int vertexShader = loadShader(GLES30.GL_VERTEX_SHADER, vertexSource);
        if (vertexShader == 0) {
            Log.e(TAG, "vertexShader: " + vertexShader);
            return 0;
        }
        Log.i(TAG, "vertexShader: " + vertexShader);
        int fragmentShader = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentSource);
        if (fragmentShader == 0) {
            Log.e(TAG, "fragmentShader: " + fragmentShader);
            return 0;
        }
        Log.i(TAG, "fragmentShader: " + fragmentShader);

        int program = GLES30.glCreateProgram();
        if (program == 0) {
            Log.e(TAG, "program: " + program);
            return 0;
        }
        Log.i(TAG, "program: " + program);

        GLES30.glAttachShader(program, vertexShader);
        GLES30.glAttachShader(program, fragmentShader);
        GLES30.glDeleteShader(vertexShader);
        GLES30.glDeleteShader(fragmentShader);
        GLES30.glLinkProgram(program);
        int[] linkStatus = new int[1];
        GLES30.glGetProgramiv(program, GLES30.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            Log.e(TAG, "linkStatus[0]: " + linkStatus[0]);
            Log.e(TAG, GLES30.glGetProgramInfoLog(program));
            GLES30.glDeleteProgram(program);
            return 0;
        }
        Log.i(TAG, "linkStatus[0]: " + linkStatus[0]);
        return program;
    }

    public static String loadFromAsserts(String fileName, Resources resources) {
        String result = null;
        try {
            InputStream is = resources.getAssets().open(fileName);
            int length = is.available();
            byte[] data = new byte[length];
            is.read(data);
            is.close();
            result = new String(data, StandardCharsets.UTF_8);
            result.replace("\\r\\n", "\\n");
            Log.i(TAG, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
