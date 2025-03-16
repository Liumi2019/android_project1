#version 300 es

precision mediump float;
out vec4 fragColor;

layout(location = 0) uniform vec4 Scolor;

void main() {
    fragColor = Scolor;
}