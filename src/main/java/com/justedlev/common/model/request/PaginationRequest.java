package com.justedlev.common.model.request;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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
                .map(Sorting::toSort)
                .map(sort -> PageRequest.of(getPage(), getSize(), sort))
                .orElse(PageRequest.of(getPage(), getSize()));
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Sorting {
        private String parameter;
        private Type type;

        public Sort toSort() {
            return Optional.ofNullable(getType())
                    .filter(ObjectUtils::isNotEmpty)
                    .filter(v -> StringUtils.isNotBlank(getParameter()))
                    .map(v -> {
                        if (v.equals(Type.ASC)) return Sort.by(Sort.Direction.ASC, getParameter());
                        else if (v.equals(Type.DESC)) return Sort.by(Sort.Direction.DESC, getParameter());
                        else return null;
                    })
                    .orElse(null);
        }

        public enum Type {
            DESC, ASC
        }
    }
}
