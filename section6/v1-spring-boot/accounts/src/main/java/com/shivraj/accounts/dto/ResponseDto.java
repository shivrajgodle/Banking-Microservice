package com.shivraj.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to Hold Successful Response Information")
public class ResponseDto {

    @Schema(description = "Status Code in the response")
    private String statusCode;

    @Schema(description = "Status Message in the response")
    private String statusMsg;
}
