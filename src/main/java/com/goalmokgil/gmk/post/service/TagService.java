package com.goalmokgil.gmk.post.service;


import com.goalmokgil.gmk.post.entity.Tag;
import com.goalmokgil.gmk.post.repository.PostRepository;
import com.goalmokgil.gmk.post.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    // 태그 작성
    public Tag createOrGetTag(String name) {
        return tagRepository.findByName(name)
                .orElseGet(() -> tagRepository.save(new Tag(name)));
    }
}
