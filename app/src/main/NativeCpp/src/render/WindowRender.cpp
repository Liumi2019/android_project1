//
// Created by ASUS on 2025/8/3.
//

#include "WindowRender.h"
#include "GLUtils.h"
#include <LogUtil.h>

const char *VERTEX_SHADER = R"(#version 300 es

layout(location = 0) in vec4 vPosition;

void main() {
    gl_Position = vPosition;
}
)";

const char *FRAGMENT_SHADER = R"(#version 300 es

precision mediump float;

out vec4 fragColor;

void main() {
    fragColor = vec4(0.0, 0.0, 1.0, 1.0);
}
)";

//顶点坐标
const GLfloat vVertices[] = {
        0.0f,  0.5f, 0.0f,
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f,
};

void WindowRender::init(ANativeWindow *window) {
    if (window == nullptr) {
        return;
    }

    mEglCore = new EglCore();
    mWindowSurface = new WindowSurface(mEglCore, window);
    mWindowSurface->makeCurrent();

    mProgram = GLUtils::CreateProgram(VERTEX_SHADER, FRAGMENT_SHADER);
    if (mProgram == 0) {
    }
}

void WindowRender::render() {
    if (mProgram == 0 || mWindowSurface == nullptr) {
        return;
    }

    glClear(GL_COLOR_BUFFER_BIT);
    glClearColor(1.0f, 0.0f, 0.0f, 0.5f);

    glUseProgram(mProgram);

    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, vVertices);
    glEnableVertexAttribArray(0);

    glDrawArrays(GL_TRIANGLES, 0, 3);
    mWindowSurface->swapBuffers();
}

void WindowRender::release() {
    if (mProgram != 0) {
        GLUtils::DeleteProgram(mProgram);
        mProgram = 0;
    }
    if (mWindowSurface != nullptr) {
        delete mWindowSurface;
        mWindowSurface = nullptr;
    }
    if (mEglCore != nullptr) {
        delete mEglCore;
        mEglCore = nullptr;
    }
}


