#version 460 core

in vec3 position;
in vec3 color;
in vec2 textureCoord;

out vec3 passColor;
out vec2 passTextureCoord;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 transform;
uniform float scale;

void main() {
    gl_Position = projection * view * transform * vec4(position * scale, 1.0);
    passColor = color;
    passTextureCoord = textureCoord;
}