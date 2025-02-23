package com.example.board.dto;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class PostDto {
    @Builder
    public record Request(@NotNull Long userId, @NotEmpty(message = "제목 없음") String title, @NotEmpty String content) {}

    @Builder
    public record Response(Long userId, String title, String content, LocalDateTime createdAt) {}

    public static Post toEntity(User user, PostDto.Request request) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .user(user)
                .build();
    }
    public static Response toResponse(Post post) {
        return Response.builder()
                .userId(post.getUser().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
