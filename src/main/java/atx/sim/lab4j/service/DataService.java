package atx.sim.lab4j.service;

import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;

@Service
public interface DataService {

    public BarSeries loadDataFromLocalCSV(String filePath);
}
