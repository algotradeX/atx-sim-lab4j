package atx.sim.lab4j.controller;

import atx.sim.lab4j.model.dto.request.LoadDataFromFileRequestDTO;
import atx.sim.lab4j.model.dto.response.ListLoadedSymbolResponseDTO;
import atx.sim.lab4j.model.dto.response.LoadDataFromFileResponseDTO;
import atx.sim.lab4j.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.ta4j.core.BarSeries;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("v1/symbol")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataFetchController {

    private final DataService dataService;

    @GetMapping(value = "/list")
    public ResponseEntity<ListLoadedSymbolResponseDTO> listLoadedSymbols() {
        log.info("Request received :: listLoadedSymbols");
        ListLoadedSymbolResponseDTO response = new ListLoadedSymbolResponseDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/load/file")
    public ResponseEntity<LoadDataFromFileResponseDTO> loadDataFromFile(
            @RequestBody @Valid LoadDataFromFileRequestDTO requestDTO
    ) {
        log.info("Request received :: loadDataFromFile {}", requestDTO);
        BarSeries status = dataService.loadDataFromLocalCSV(requestDTO.getFilePath());
        LoadDataFromFileResponseDTO responseDTO = new LoadDataFromFileResponseDTO();
        responseDTO.setStatus(String.valueOf(status));
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }


}
