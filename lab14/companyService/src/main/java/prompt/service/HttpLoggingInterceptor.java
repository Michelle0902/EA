package prompt.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        logAIRequest(request, body);

        ClientHttpResponse response = execution.execute(request, body);

        BufferedResponseWrapper wrapped = new BufferedResponseWrapper(response);

        logAIResponse(wrapped);

        return wrapped;
    }

    private void logAIRequest(HttpRequest request, byte[] body) {
        System.out.println("AI REQUEST: method=" + request.getMethod()
                + ", URL=" + request.getURI());
        if (body != null && body.length > 0) {
            System.out.println("BODY: " + new String(body, StandardCharsets.UTF_8));
        }
    }

    private void logAIResponse(ClientHttpResponse response) throws IOException {
        HttpStatusCode status = response.getStatusCode();
        System.out.println("AI RESPONSE STATUS: " + status.value() + " " + response.getStatusText());
        String respBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("BODY: " + respBody.trim());
    }

    static class BufferedResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse delegate;
        private final byte[] bodyBytes;

        BufferedResponseWrapper(ClientHttpResponse delegate) throws IOException {
            this.delegate = delegate;
            this.bodyBytes = delegate.getBody().readAllBytes();
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return delegate.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return delegate.getStatusText();
        }

        @Override
        public void close() {
            delegate.close();
        }

        @Override
        public InputStream getBody() {
            return new ByteArrayInputStream(bodyBytes);
        }

        @Override
        public HttpHeaders getHeaders() {
            return delegate.getHeaders();
        }
    }
}