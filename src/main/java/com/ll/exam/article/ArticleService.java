package com.ll.exam.article;

import com.ll.exam.Rq;

public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService() {
        this.articleRepository = new ArticleRepository();
    }


    public long write(String title, String body) {
        return articleRepository.write(title,body);
    }
}
