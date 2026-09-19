package in.brainupgrade.transactionservice.payroll.client;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class PostingClient {

    private final RestClient restClient;

    public PostingClient(RestClient.Builder builder,
                         @Value("${posting.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public PostingResponse post(PostingRequest request) {
        try {
            return restClient.post()
                    .uri("/api/v1/postings")
                    .body(request)
                    .retrieve()
                    .body(PostingResponse.class);
        } catch (RestClientException ex) {
            throw new PostingClientException(
                    "Posting " + request.getClientReference() + " was rejected by global-bank-account", ex);
        }
    }

    /**
     * Asks the posting service for the settlement date that applies to a value date,
     * so this service does not have to model the settlement calendar itself.
     */
    public LocalDate resolveSettlementDate(LocalDate valueDate) {
        return restClient.get()
                .uri("/api/v1/settlement-date?valueDate={valueDate}", valueDate)
                .retrieve()
                .body(LocalDate.class);
    }
}
