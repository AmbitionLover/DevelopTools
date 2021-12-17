package work.ambitlu.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import work.ambitlu.task.AppTokenFactory;

/**
 * work.ambitlu.listener
 *
 * @author Ambi 赵帅
 * @Month 12
 */
public class GlobalPropertiesListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {

	@Autowired
	private AppTokenFactory appTokenFactory;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		appTokenFactory.executeTask();
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
