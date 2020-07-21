package atx.sim.lab4j.sample;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class build a Ta4j bar series from a CSV file containing bars.
 */
@Slf4j
public class CsvBarsLoader {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * @return the bar series from Apple Inc. bars.
     */
    public static BarSeries loadAppleIncSeries() {
        return loadCsvSeries("appleinc_bars_from_20130101_usd.csv");
    }

    public static BarSeries loadCsvSeries(String filename) {

        InputStream stream = CsvBarsLoader.class.getClassLoader().getResourceAsStream(filename);

        BarSeries series = new BaseBarSeries("apple_bars");

        assert stream != null;

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                ZonedDateTime date = LocalDate.parse(line[0], DATE_FORMAT).atStartOfDay(ZoneId.systemDefault());
                double open = Double.parseDouble(line[1]);
                double high = Double.parseDouble(line[2]);
                double low = Double.parseDouble(line[3]);
                double close = Double.parseDouble(line[4]);
                double volume = Double.parseDouble(line[5]);

                series.addBar(date, open, high, low, close, volume);
            }
        } catch (IOException ioe) {
            Logger.getLogger(CsvBarsLoader.class.getName()).log(Level.SEVERE, "Unable to load bars from CSV", ioe);
        } catch (NumberFormatException | CsvValidationException nfe) {
            Logger.getLogger(CsvBarsLoader.class.getName()).log(Level.SEVERE, "Error while parsing value", nfe);
        }
        return series;
    }

    public static void main(String[] args) {
        BarSeries series = CsvBarsLoader.loadAppleIncSeries();

        log.info("Series: " + series.getName() + " (" + series.getSeriesPeriodDescription() + ")");
        log.info("Number of bars: " + series.getBarCount());
        log.info("First bar: \n" + "\tVolume: " + series.getBar(0).getVolume() + "\n" + "\tOpen price: "
                + series.getBar(0).getOpenPrice() + "\n" + "\tClose price: " + series.getBar(0).getClosePrice());
    }
}
