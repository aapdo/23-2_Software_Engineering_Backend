package com.goalmokgil.gmk.post.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqLikeDto {
    private Long userId;
    private Long postId;
}
