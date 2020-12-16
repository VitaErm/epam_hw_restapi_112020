package test.java.restApi;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Order.OrderBuilder.class)
@Builder
public class Order {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderBuilder {

    }
}
