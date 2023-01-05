package org.suai.restApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SensorDTO {

    @Size(min = 3, max = 30, message = "Имя должно быть больше 3 символов и меньше 30 символов")
    private String name;

}
