package com.klef.jfsd.exam.service;

import com.klef.jfsd.exam.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final RestTemplate restTemplate;

    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Comment> getAllComments() {
        String url = "https://jsonplaceholder.typicode.com/comments";
        logger.info("Fetching comments from external API: {}", url);

        Comment[] comments = restTemplate.getForObject(url, Comment[].class);
        if (comments == null || comments.length == 0) {
            logger.error("No comments returned from the external API.");
            throw new RuntimeException("No comments available from the external API.");
        }

        logger.info("Successfully fetched {} comments from external API.", comments.length);
        return Arrays.asList(comments);
    }
}
