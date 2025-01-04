package com.paravar.instacred.common.models;

public record PageParams(Integer pageNo, Integer pageSize) {
    public PageParams of(int pageNo, int pageSize) {
        return new PageParams(pageNo, pageSize);
    }

    public static PageParams of(Integer pageNo, Integer pageSize, Integer defaultPageSize, Integer maxPageSize) {
        pageNo = pageNo == null || pageNo <= 0 ? 0 : pageNo - 1; //
        pageSize = (pageSize == null || pageSize <= 0) ? defaultPageSize : pageSize;
        pageSize = Math.min(pageSize, maxPageSize); // not more than max page size
        return new PageParams(pageNo, pageSize);
    }
}
