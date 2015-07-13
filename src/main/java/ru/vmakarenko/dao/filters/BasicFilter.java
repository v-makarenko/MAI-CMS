package ru.vmakarenko.dao.filters;

import ru.vmakarenko.dao.anno.FilterParam;

/**
 * Created by vmakarenko on 04.03.2015.
 */
public class BasicFilter {
    @FilterParam
    private final ThreadLocal<Long> limit = new ThreadLocal<>();
    @FilterParam
    private Long page;
    @FilterParam
    private Long start;
    private String orderDir;
    private String orderColumn;

    public BasicFilter() {
    }

    public BasicFilter(Long limit, Long page, Long start) {
        this.limit.set(limit);
        this.page = page;
        this.start = start;
    }

    public Long getLimit() {
        return limit.get();
    }

    public void setLimit(Long limit) {
        this.limit.set(limit);
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public BasicFilter orderDir(String orderDir) {
        this.orderDir = orderDir;
        return this;
    }

    public BasicFilter orderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
        return this;
    }
}
