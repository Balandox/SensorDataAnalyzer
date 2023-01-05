package org.suai.restApp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.suai.restApp.models.Sensor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeasurementDTO {

    @NotNull(message = "Значение температуры не может быть пустым")
    @Min(value = -100, message = "Минимально возможная температура -100 градусов")
    @Max(value = 100, message = "Максимально возможная температура 100 градусов")
    private Float value;

    @NotNull(message = "Отсутствуют данные о дожде")
    private Boolean raining;

    @NotNull(message = "Имя сенсора отсутствует")
    private SensorDTO sensor;

}
