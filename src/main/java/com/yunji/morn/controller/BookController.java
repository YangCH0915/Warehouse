package com.yunji.morn.controller;

import com.yunji.morn.bean.Book;
import com.yunji.morn.dto.ExecutionResult;
import com.yunji.morn.enums.AppointStateEnum;
import com.yunji.morn.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private String list(Model model) {
        List<Book> list = bookService.getList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping("/test")
    public int testError(){
        return 9 / 0;  // 除 0异常
    }

    @RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
    private String detail(@PathVariable("bookId") Long bookId, Model model) {
        if (bookId == null) {
            return "redirect:/book/list";
        }
        Book book = bookService.getById(bookId);
        if (book == null) {
            return "forward:/book/list";
        }
        model.addAttribute("book", book);
        return "detail";
    }

    //ajax json
    @RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST,
            produces = {"application/json; charset=utf-8" })
    @ResponseBody
    private ExecutionResult appoint(HttpServletResponse response, @PathVariable("bookId") Long bookId, @RequestParam("studentId") Long studentId) {
        response.setHeader("Access-Control-Allow-Origin","*");
        if (studentId == null || studentId.equals("")) {

        }
        ExecutionResult execution = null;
        try {
            execution = bookService.appoint(bookId, studentId);
        } catch (Exception e1) {
            execution = new ExecutionResult(bookId, AppointStateEnum.NO_NUMBER);
        }
        return execution;
    }
}
