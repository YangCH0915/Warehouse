package com.yunji.morn.dto;

import com.yunji.morn.bean.Appointment;
import com.yunji.morn.enums.AppointStateEnum;

public class ExecutionResult {

    // 图书ID
    private long bookId;
    // 结果状态
    private int state;
    // 状态标识
    private String stateInfo;
    // 预约成功对象
    private Object data;

    public ExecutionResult() {
    }

    // 预约失败的构造器
    public ExecutionResult(long bookId, AppointStateEnum stateEnum) {
        this.bookId = bookId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 预约成功的构造器
    public ExecutionResult(long bookId, AppointStateEnum stateEnum, Object data) {
        this.bookId = bookId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.data = data;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExecutionResult{" +
                "bookId=" + bookId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", data=" + data +
                '}';
    }
}
