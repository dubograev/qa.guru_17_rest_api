import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "message",
        "updatetopcartsectionhtml",
        "updateflyoutcartsectionhtml"
})
public class CartResponse {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("updatetopcartsectionhtml")
    private String updatetopcartsectionhtml;
    @JsonProperty("updateflyoutcartsectionhtml")
    private String updateflyoutcartsectionhtml;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("updatetopcartsectionhtml")
    public String getUpdatetopcartsectionhtml() {
        return updatetopcartsectionhtml;
    }

    @JsonProperty("updatetopcartsectionhtml")
    public void setUpdatetopcartsectionhtml(String updatetopcartsectionhtml) {
        this.updatetopcartsectionhtml = updatetopcartsectionhtml;
    }

    @JsonProperty("updateflyoutcartsectionhtml")
    public String getUpdateflyoutcartsectionhtml() {
        return updateflyoutcartsectionhtml;
    }

    @JsonProperty("updateflyoutcartsectionhtml")
    public void setUpdateflyoutcartsectionhtml(String updateflyoutcartsectionhtml) {
        this.updateflyoutcartsectionhtml = updateflyoutcartsectionhtml;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}