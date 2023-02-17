package com.justedlev.common.model.request;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

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
    private Sorting sorting;

    public PageRequest toPageRequest() {
        return Optional.ofNullable(getSorting())
                .filter(Sorting::isSortable)
                .map(this::toSort)
                .map(sort -> PageRequest.of(getPage(), getSize(), sort))
                .orElse(PageRequest.of(getPage(), getSize()));
    }

    private Sort toSort(Sorting sorting) {
        return Optional.ofNullable(sorting)
                .filter(Sorting::isSortable)
                .map(v -> {
                    if (v.getType().equals(Sorting.Type.ASC))
                        return Sort.by(Sort.Direction.ASC, v.getArrayParameters());
                    else if (v.getType().equals(Sorting.Type.DESC))
                        return Sort.by(Sort.Direction.DESC, v.getArrayParameters());
                    else return null;
                })
                .orElse(null);
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Sorting {
        private Set<String> parameters;
        private Type type;

        public boolean isSortable() {
            return CollectionUtils.isNotEmpty(getParameters()) && ObjectUtils.isNotEmpty(getType());
        }

        public String[] getArrayParameters() {
            return getParameters().stream()
                    .filter(StringUtils::isNotBlank)
                    .toArray(String[]::new);
        }

        public enum Type {
            DESC, ASC
        }
    }
}
