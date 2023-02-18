package com.justedlev.common.model.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;

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
    private Boolean hasNext = Boolean.FALSE;
    @Builder.Default
    private Boolean hasPrevious = Boolean.FALSE;
    @Builder.Default
    private Collection<C> content = Collections.emptyList();

    public static <T, C> PageResponse<C> fromWithContent(Page<T> page, Collection<C> content) {
        return PageResponse.<C>builder()
                .pageNo(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .content(content)
                .build();
    }
}
