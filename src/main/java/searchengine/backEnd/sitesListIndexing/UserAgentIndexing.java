package searchengine.backEnd.sitesListIndexing;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import searchengine.config.UserAgentList;

@Service
@RequiredArgsConstructor
public class UserAgentIndexing {


    @Bean
    public static String getUserAgentName(UserAgentList userAgentList) {
        return userAgentList.getOptions().getName();
    }

    @Primary
    public static String getUserAgentReferrer(UserAgentList userAgentList) {
        return userAgentList.getOptions().getReferrer();
    }
}
