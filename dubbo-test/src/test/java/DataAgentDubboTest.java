import com.zdmoney.data.agent.api.base.ResultDto;
import com.zdmoney.data.agent.api.dto.safe.AccessChannel;
import com.zdmoney.data.agent.api.dto.safe.CustomerVisitRecordDto;
import com.zdmoney.data.agent.api.facade.IDataAgentSafeFacadeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DataAgentDubboTest {

    private IDataAgentSafeFacadeService dataAgentSafeFacadeService;

    @org.junit.Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        dataAgentSafeFacadeService = (IDataAgentSafeFacadeService) ac.getBean("dataAgentSafeFacadeService");
    }

    @org.junit.Test
    public void test() {

        ExecutorService resetExecutor = new ThreadPoolExecutor(30, 30, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            resetExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                    System.out.println("--" + threadGroup.getName());
                    CustomerVisitRecordDto reqDto = new CustomerVisitRecordDto();
                    reqDto.setAccessChannel(AccessChannel.ANDROID);
                    reqDto.setCustomerId("00004" + finalI);
                    reqDto.setRequestTime(System.currentTimeMillis());
                    reqDto.setResponseTime(System.currentTimeMillis());
                    ResultDto<String> resultDto = dataAgentSafeFacadeService.customerVisitRecord(reqDto);
                    System.out.println(resultDto.getCode());
                }
            });
        }
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
