package com.cp.openai.domain;

public record Poem(
    String title,
    String content,
    String genre,
    String theme) {
}