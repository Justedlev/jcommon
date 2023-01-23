package com.justedlev.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Builder.Default
    private Date timestamp = new Date();
    private String message;
    private String details;
}
