package com.justedlev.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageResponse<C> {
    @Builder.Default
    private Integer pageNo = 1;
    @Builder.Default
    private Integer totalPages = 1;
    @Builder.Default
    private Long totalElements = 0L;
    @Builder.Default
    private Integer size = 0;
    @Builder.Default
    private Boolean hasNext = Boolean.FALSE;
    @Builder.Default
    private Boolean hasPrevious = Boolean.FALSE;
    @Builder.Default
    private List<C> content = Collections.emptyList();

    public static <I, O> PageResponse<O> from(Page<I> page, Function<I, O> converter) {
        return from(page.map(converter));
    }

    public static <I> PageResponse<I> from(Page<I> page) {
        return PageResponse.<I>builder()
                .pageNo(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .build();
    }
}
