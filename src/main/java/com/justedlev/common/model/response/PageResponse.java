package com.justedlev.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageResponse<D> {
    @Builder.Default
    private Integer pageNo = 1;
    @Builder.Default
    private Integer totalPages = 1;
    @Builder.Default
    private Boolean hasNext = Boolean.FALSE;
    @Builder.Default
    private Boolean hasPrevious = Boolean.FALSE;
    @Builder.Default
    private Collection<D> content = Collections.emptyList();

    public static <T, D> PageResponse<D> fromWithContent(Page<T> page, Collection<D> content) {
        return PageResponse.<D>builder()
                .pageNo(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .content(content)
                .build();
    }
}
