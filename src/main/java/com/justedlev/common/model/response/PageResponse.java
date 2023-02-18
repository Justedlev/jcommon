package com.justedlev.common.model.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;
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
    private Boolean hasNext = Boolean.FALSE;
    @Builder.Default
    private Boolean hasPrevious = Boolean.FALSE;
    @Builder.Default
    private Collection<C> content = Collections.emptyList();

    public static <T, C> PageResponse<C> from(Page<T> page, Function<T, C> converter) {
        var converted = page.map(converter);

        return PageResponse.<C>builder()
                .pageNo(converted.getNumber() + 1)
                .totalPages(converted.getTotalPages())
                .hasNext(converted.hasNext())
                .hasPrevious(converted.hasPrevious())
                .content(converted.getContent())
                .build();
    }
}
