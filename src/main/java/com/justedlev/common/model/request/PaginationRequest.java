package com.justedlev.common.model.request;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Min(value = 1, message = "Page number cannot be less then 1.")
    @NotNull(message = "Page number cannot be null.")
    @Builder.Default
    private Integer page = 1;
    @Min(value = 1, message = "Page size cannot be less then 1.")
    @NotNull(message = "Page size cannot be null.")
    @Builder.Default
    private Integer size = 1;
    private Sorting sorting;

    public Pegeable toPegeable() {
        return Optional.ofNullable(getSorting())
                .map(Sorting::toSort)
                .map(sort -> PageRequest.of(getPage() - 1, getSize(), sort))
                .orElse(PageRequest.of(getPage(), getSize()));
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Sorting {
        private Set<String> parameters;
        private Direction direction;

        public boolean isSortable() {
            return CollectionUtils.isNotEmpty(getParameters()) && ObjectUtils.isNotEmpty(getDirection());
        }

        public Sort toSort() {
            if (!isSortable()) return Sort.unsorted();
            else if (getDirection().equals(Direction.ASC))
                return Sort.by(Sort.Direction.ASC, parametersAsArray());
            else if (getDirection().equals(Direction.DESC))
                return Sort.by(Sort.Direction.DESC, parametersAsArray());
            else return Sort.unsorted();
        }

        private String[] parametersAsArray() {
            return getParameters().stream()
                    .filter(StringUtils::isNotBlank)
                    .toArray(String[]::new);
        }

        public enum Direction {
            DESC, ASC
        }
    }
}
