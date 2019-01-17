import com.zdmoney.data.core.api.common.dto.ResultDto;
import com.zdmoney.data.core.api.facade.IDataCoreUserFacadeService;
import com.zdmoney.data.core.api.user.UserGroupResDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DataCoreDubboTest {

    private IDataCoreUserFacadeService dataCoreUserFacadeService;

    @org.junit.Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        dataCoreUserFacadeService = (IDataCoreUserFacadeService) ac.getBean("dataCoreUserFacadeService");
    }

    @org.junit.Test
    public void test() {
        ResultDto<List<UserGroupResDto>> groupList = dataCoreUserFacadeService.getGroupList();
        System.out.println(groupList.getCode());
    }

}
