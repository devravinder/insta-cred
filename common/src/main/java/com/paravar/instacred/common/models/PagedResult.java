package com.paravar.instacred.common.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class PagedResult<T> {
    private List<T> data;
    private long elements;
    private long totalElements;
    private int pageNumber;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public static <T> PagedResult<T> of(Page<T> page) {
        var content = page.getContent();
        return new PagedResult<>(
                content,
                content.size(),
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious());
    }
}
