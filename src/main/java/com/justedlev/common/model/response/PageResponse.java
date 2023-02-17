package com.justedlev.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
