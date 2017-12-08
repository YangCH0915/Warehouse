package com.yunji.morn.service;

import com.yunji.morn.bean.Book;
import com.yunji.morn.dto.ExecutionResult;
import java.util.List;

public interface BookService {
    /**
     * 查询一本图书
     *
     * @param bookId
     * @return
     */
    Book getById(long bookId);

    /**
     * 查询所有图书
     *
     * @return
     */
    List<Book> getList();

    /**
     * 预约图书
     *
     * @param bookId
     * @param studentId
     * @return
     */
    ExecutionResult appoint(long bookId, long studentId);
}
