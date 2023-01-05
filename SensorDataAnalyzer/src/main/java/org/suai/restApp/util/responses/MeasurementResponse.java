package org.suai.restApp.util.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.suai.restApp.dto.MeasurementDTO;
import org.suai.restApp.models.Measurement;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeasurementResponse {

    private List<MeasurementDTO> measurements;

}
