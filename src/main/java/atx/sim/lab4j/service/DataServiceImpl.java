package atx.sim.lab4j.service;

import atx.sim.lab4j.util.CSVReaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataServiceImpl implements DataService {

    private final CSVReaderUtil csvReaderUtil;

    @Override
    public BarSeries loadDataFromLocalCSV(String filePath) {
        log.debug("loadDataFromLocalCSV : reading file '{}'", filePath);
        BarSeries barSeries = null;
        try {
            barSeries = csvReaderUtil.generateBarSeriesFromCSV(filePath);
        } catch(IOException e) {
            log.error("IOException");
        }
        return barSeries;
    }
}
