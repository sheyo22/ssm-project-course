package org.example.crm.vo;

import java.util.List;

public class PaginationVO<T> {
    private int total;
    private List<T> pageList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
