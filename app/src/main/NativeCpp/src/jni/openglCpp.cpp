
#include <jni.h>
#include <string>
#include "printStr.h"
#include "WindowRender.h"

#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <GLES3/gl3.h>
#include "LogUtil.h"



extern "C" jint
Java_learnopengl_secondgl_SecondGl_nativeSetSurface(JNIEnv *env, jobject obj, jobject surface) {
    if (surface == nullptr) {
        LOGCATI("nativeSetSurface: surface is null");
        return -1;
    }

    auto *g_windowRender = new WindowRender();
    if (g_windowRender == nullptr) {
        LOGCATI("nativeSetSurface: failed to create WindowRender");
        return -1;
    }

    ANativeWindow *window = ANativeWindow_fromSurface(env, surface);
    if (window == nullptr) {
        LOGCATI("nativeSetSurface: ANativeWindow_fromSurface failed");
        delete g_windowRender;
        return -1;
    }
    LOGCATI("ANativeWindow_fromSurface window = %p", window);

    g_windowRender->init(window);
    g_windowRender->render();

    delete g_windowRender;
    g_windowRender = nullptr;
    ANativeWindow_release(window);
    return 0;
}

