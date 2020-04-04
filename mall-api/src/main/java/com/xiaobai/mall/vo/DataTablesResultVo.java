package com.xiaobai.mall.vo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 江湖人称白玉汤
 * @date 2020/3/2
 */
public class DataTablesResultVo implements Serializable{

    private Boolean success;

    private Integer draw;

    private Integer recordsTotal;

    private Integer recordsFiltered;

    private String error;

    private List<?> data;

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
