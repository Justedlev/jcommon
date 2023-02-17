package com.justedlev.common.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationRequest {
    @Min(value = 0, message = "Page number cannot be less then 1.")
    @NotNull(message = "Page number cannot be null.")
    private Integer page;
    @Min(value = 1, message = "Page size cannot be less then 1.")
    @NotNull(message = "Page size cannot be null.")
    private Integer size;
    private String sortBy;
    @Builder.Default
    private SortType sortType = SortType.NONE;

    public enum SortType {
        NONE, DESC, ASC
    }
}
