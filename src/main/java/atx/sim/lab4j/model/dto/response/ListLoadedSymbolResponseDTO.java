package atx.sim.lab4j.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ListLoadedSymbolResponseDTO {

    private List<String> loadedSymbols;
}
