package searchengine.backEnd.configStatus;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ConfigStatus {
    Boolean result;

    public ConfigStatus() {
    }

    public ConfigStatus(Boolean result) {
        this.result = result;
    }
}
