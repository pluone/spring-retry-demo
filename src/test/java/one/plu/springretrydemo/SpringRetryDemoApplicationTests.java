package one.plu.springretrydemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRetryDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private MyService myService;

	@Autowired
	private RetryTemplate retryTemplate;

	@Test(expected = RuntimeException.class)
	public void givenRetryService_whenCallWithException_thenRetry() {
		myService.retryService();
	}

	@Test
	public void givenRetryServiceWithRecovery_whenCallWithException_thenRetryRecover() throws SQLException {
		myService.retryServiceWithRecovery(null);
	}

	@Test(expected = RuntimeException.class)
	public void givenTemplateRetryService_whenCallWithException_thenRetry() {
		retryTemplate.execute(arg0 -> {
			myService.templateRetryService();
			return null;
		});
	}
}
