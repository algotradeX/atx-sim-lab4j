package atx.sim.lab4j.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoadDataFromFileRequestDTO {

    @NotNull
    @NotBlank
    private String filePath;
}
