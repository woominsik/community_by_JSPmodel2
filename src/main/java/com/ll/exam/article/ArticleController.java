package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {

    private ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }


    public void showList(Rq rq){
        List<ArticleDto> articleDtos = articleDtos = articleService.findAll();

        rq.setAttr("articles", articleDtos);
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title","");
        String body = rq.getParam("body","");

        long id = articleService.write(title, body);

        rq.appendBody("<div>title : %s</div>".formatted(title));
        rq.appendBody("<div>body : %s</div>".formatted(body));
    }

    public void showDetail(Rq rq){
        long id =rq.getLongPathValueByIndex(1,0);

        if (id == 0) {
            rq.appendBody("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }
        rq.setAttr("article",articleDto);
        rq.view("/usr/article/detail");
    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1,0);

        if (id == 0) {
            rq.appendBody("번호를 입력해주세요.");
            return;
        }
        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article",articleDto);
        rq.view("/usr/article/modify");
    }

    public void doDelete(Rq rq) {

    }

    public void doModify(Rq rq) {
        long id =rq.getLongPathValueByIndex(1,0);

        String title = rq.getParam("title","");
        String body = rq.getParam("body","");

        articleService.modify(id, title,body);

        ArticleDto articleDto = articleService.findById(id);
        rq.setAttr("article",articleDto);
        rq.view("/usr/article/detail");
    }
}
