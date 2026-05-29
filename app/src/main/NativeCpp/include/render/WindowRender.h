//
// Created by ASUS on 2025/8/3.
//
#include "EglCore.h"
#include "WindowSurface.h"

#include <GLES3/gl3.h>


#ifndef APP_WINDOWRENDER_H
#define APP_WINDOWRENDER_H


class WindowRender {

public:
    WindowRender() = default;
    ~WindowRender() = default;

    void init(ANativeWindow *window);

    void render();

    void release();

private:
    EglCore *mEglCore = nullptr;
    WindowSurface *mWindowSurface = nullptr;
    GLuint mProgram = 0;
};


#endif //APP_WINDOWRENDER_H
