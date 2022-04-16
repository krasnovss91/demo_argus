package dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArgusDto {
    private Integer docNumber;

    private Date date;

    private Integer blancNumber;

    private Integer inn;
}
