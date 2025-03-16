#version 300 es

layout(location = 0) in vec4 vPosition;
layout(location = 1) in float PointSize;

vec4 m = vec4(1.0, 0.2, 0.3, 0.0);
void main() {
    gl_Position = (vPosition + m);
    gl_PointSize = PointSize;
}
