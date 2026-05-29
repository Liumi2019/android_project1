//
// Created by Administrator on 2018/3/21.
//

#include <cassert>
#include <GLES2/gl2.h>
#include <LogUtil.h>
#include "EglSurfaceBase.h"


EglSurfaceBase::EglSurfaceBase(EglCore *eglCore) : mEglCore(eglCore) {
    mEglSurface = EGL_NO_SURFACE;
    mHeight = -1;
    mWidth = -1;
}

/**
 * 创建显示的Surface
 * @param nativeWindow
 */
void EglSurfaceBase::createWindowSurface(ANativeWindow *nativeWindow) {
    assert(mEglSurface == EGL_NO_SURFACE);
    if (mEglSurface != EGL_NO_SURFACE) {
        LOGCATE("surface already created\n");
        return;
    }
    mEglSurface = mEglCore->createWindowSurface(nativeWindow);
}

/**
 * 创建离屏surface
 * @param width
 * @param height
 */
void EglSurfaceBase::createOffscreenSurface(int width, int height) {
    assert(mEglSurface == EGL_NO_SURFACE);
    if (mEglSurface != EGL_NO_SURFACE) {
        LOGCATE("surface already created\n");
        return;
    }
    mEglSurface = mEglCore->createOffscreenSurface(width, height);
    mWidth = width;
    mHeight = height;
}

/**
 * 获取宽度
 * @return
 */
int EglSurfaceBase::getWidth() {
    if (mWidth < 0) {
        return mEglCore->querySurface(mEglSurface, EGL_WIDTH);
    } else {
        return mWidth;
    }
}

/**
 * 获取高度
 * @return
 */
int EglSurfaceBase::getHeight() {
    if (mHeight < 0) {
        return mEglCore->querySurface(mEglSurface, EGL_HEIGHT);
    } else {
        return mHeight;
    }
}

/**
 * 释放EGLSurface
 */
void EglSurfaceBase::releaseEglSurface() {
    mEglCore->releaseSurface(mEglSurface);
    mEglSurface = EGL_NO_SURFACE;
    mWidth = mHeight = -1;
}

/**
 * 切换到当前EGLContext
 */
void EglSurfaceBase::makeCurrent() {
    mEglCore->makeCurrent(mEglSurface);
}

/**
 * 交换到前台显示
 * @return
 */
bool EglSurfaceBase::swapBuffers() {
    bool result = mEglCore->swapBuffers(mEglSurface);
    if (!result) {
        LOGCATD("WARNING: swapBuffers() failed");
    }
    return result;
}

/**
 * 设置当前时间戳
 * @param nsecs
 */
void EglSurfaceBase::setPresentationTime(long nsecs) {
    mEglCore->setPresentationTime(mEglSurface, nsecs);
}

/**
 * 获取当前像素
 * @return
 */
char* EglSurfaceBase::getCurrentFrame() {
    int w = getWidth();
    int h = getHeight();
    if (w <= 0 || h <= 0) {
        LOGCATD("WARNING: getCurrentFrame invalid size %dx%d", w, h);
        return nullptr;
    }
    size_t bufSize = (size_t)w * h * 4;
    char *pixels = new char[bufSize];
    glReadPixels(0, 0, w, h, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
    return pixels;
}
