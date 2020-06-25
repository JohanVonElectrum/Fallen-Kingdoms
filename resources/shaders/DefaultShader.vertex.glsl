#version 140

in vec2 position;
in vec2 uvs;

uniform vec4 matColor;
uniform mat4 projection;
out vec4 color;

void main() {
    vec2 worldPosition = position;

    gl_Position = projection * vec4(worldPosition, 0, 1);
    color = matColor;
}
