package prompt.config;

import prompt.service.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean("profitRestClient")
    RestClient profitRestClient(@Value("${profit.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor(new HttpLoggingInterceptor())
                .build();
    }

    @Bean("currencyRestClient")
    RestClient currencyRestClient(@Value("${currency.base-url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor(new HttpLoggingInterceptor())
                .build();
    }
}

