import com.zdmoney.data.agent.api.base.ResultDto;
import com.zdmoney.data.agent.api.dto.safe.AccessChannel;
import com.zdmoney.data.agent.api.dto.safe.CustomerVisitRecordDto;
import com.zdmoney.data.agent.api.facade.IDataAgentSafeFacadeService;
import com.zdmoney.data.core.api.facade.IDataCoreUserFacadeService;
import com.zdmoney.data.core.api.user.UserGroupResDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DataAgentDubboTest {

    private IDataAgentSafeFacadeService dataAgentSafeFacadeService;

    @org.junit.Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        dataAgentSafeFacadeService = (IDataAgentSafeFacadeService) ac.getBean("dataAgentSafeFacadeService");
    }

    @org.junit.Test
    public void test() {
        CustomerVisitRecordDto reqDto = new CustomerVisitRecordDto();
        reqDto.setAccessChannel(AccessChannel.ANDROID);
        reqDto.setCustomerId("00001");
        ResultDto<String> resultDto = dataAgentSafeFacadeService.customerVisitRecord(reqDto);
        System.out.println(resultDto.getCode());


    }

}
