package org.suai.restApp.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.suai.restApp.dto.MeasurementDTO;

import java.util.ArrayList;
import java.util.List;

public class MeasurementResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public MeasurementResponse() {
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

}
