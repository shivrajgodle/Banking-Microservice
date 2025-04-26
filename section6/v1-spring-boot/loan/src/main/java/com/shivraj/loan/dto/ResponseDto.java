package com.shivraj.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold Successful error responses")
@Data
@AllArgsConstructor
public class ResponseDto {

    @Schema(description = "Status Code in the response")
    private String statusCode;

    @Schema(description = "Status message in the response")
    private String statusMessage;

}
