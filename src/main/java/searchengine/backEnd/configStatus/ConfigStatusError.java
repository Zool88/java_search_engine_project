package searchengine.backEnd.configStatus;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ConfigStatusError {
    Boolean result;
    String error;

    public ConfigStatusError() {
    }

    public ConfigStatusError(Boolean result, String error) {
        this.result = result;
        this.error = error;
    }
}
