package com.yunji.morn.service.impl;

import com.yunji.morn.bean.Appointment;
import com.yunji.morn.bean.Book;
import com.yunji.morn.dao.AppointmentDao;
import com.yunji.morn.dao.BookDao;
import com.yunji.morn.dto.ExecutionResult;
import com.yunji.morn.enums.AppointStateEnum;
import com.yunji.morn.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // 注入Service依赖
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AppointmentDao appointmentDao;


    public Book getById(long bookId) {
        return bookDao.queryById(bookId);
    }

    public List<Book> getList() {
        return bookDao.queryAll(0, 1000);
    }

    /**
     * 使用注解控制事务方法的优点：
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    @Transactional
    public ExecutionResult appoint(long bookId, long studentId) {
        // 减库存
        try{
            int update = bookDao.reduceNumber(bookId);
            if (update <= 0) {// 库存不足
                return new ExecutionResult(bookId, AppointStateEnum.NO_NUMBER);
            } else {
                // 执行预约操作
                int insert = appointmentDao.insertAppointment(bookId, studentId);
                if (insert <= 0) {// 重复预约
                    return new ExecutionResult(bookId, AppointStateEnum.REPEAT_APPOINT);
                } else {// 预约成功
                    Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
                    return new ExecutionResult(bookId, AppointStateEnum.SUCCESS, appointment);
                }
            }
        }catch (Exception e){
            return new ExecutionResult(bookId, AppointStateEnum.INNER_ERROR);
        }
    }
}
