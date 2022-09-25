package meteorogical.client.dto;

import java.util.List;

public class MeasurementsResponse {
    List<MeasurementDTO> measurement;

    public List<MeasurementDTO> getMeasurements() {
        return measurement;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurement = measurements;
    }
}