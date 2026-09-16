package in.brainupgrade.transactionservice;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransactionserviceApplication {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TransactionserviceApplication.class);

	public static void main(String[] args) {
		log.info("TransactionserviceApplication is started");
		SpringApplication.run(TransactionserviceApplication.class, args);
	}

}
