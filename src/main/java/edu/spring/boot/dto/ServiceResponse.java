package edu.spring.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> {
    private HttpStatus status;
    private T response;
    private List<ErrorDTO> error;

    public ServiceResponse(HttpStatus status, T response) {
        this.status = status;
        this.response = response;
    }
}
