package ragwithpdf;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));

        SpringApplication.run(Application.class, args);
    }

    @Value("classpath:/IphoneUserGuide.pdf")
    private Resource documentResource;
    @Autowired
    VectorStore vectorStore;
    @Autowired
    VectorStoreRepository vectorStoreRepository;

    @Override
    public void run(String... args) throws Exception {
        vectorStoreRepository.cleanUpStore();

        TikaDocumentReader documentReader = new TikaDocumentReader(documentResource);
        TextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.add(
                textSplitter.apply(
                        documentReader.get()));
    }
}
