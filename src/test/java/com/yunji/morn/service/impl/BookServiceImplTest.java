package com.yunji.morn.service.impl;

import com.yunji.morn.BaseTest;
import com.yunji.morn.dto.ExecutionResult;
import com.yunji.morn.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testAppoint() throws Exception {
        long bookId = 1001;
        long studentId = 12345678910L;
        ExecutionResult execution = bookService.appoint(bookId, studentId);
        System.out.println(execution);
    }
}
