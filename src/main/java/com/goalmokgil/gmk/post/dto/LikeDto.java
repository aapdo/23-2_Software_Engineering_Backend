package com.goalmokgil.gmk.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto {
    private Long userId;
    private Long postId;
}
