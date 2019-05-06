package com.xust.base;

/**
 * Datatables响应结构
 * @author: Luo Daiyang
 * @description:
 * @date Created in 9:16 2019/5/4
 * @modified By:
 */
public class ApiDataTableResponse extends ApiResponse {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;

    public ApiDataTableResponse(ApiResponse.Status status) {
        this(status.getCode(), status.getStandardMessage(), null);
    }

    public ApiDataTableResponse(int code, String message, Object data) {
        super(code, message, data);
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

}
