package handler;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class menuDTO {

    private String name;
    private int price;

    private int categoryCode;
    private String orderableStatus;
}
