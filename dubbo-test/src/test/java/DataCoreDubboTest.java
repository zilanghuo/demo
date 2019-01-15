import com.zdmoney.data.core.api.facade.IDataCoreUserFacadeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataCoreDubboTest {

    private IDataCoreUserFacadeService dataCoreUserFacadeService;

    @org.junit.Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        dataCoreUserFacadeService = (IDataCoreUserFacadeService) ac.getBean("dataCoreUserFacadeService");
    }

    @org.junit.Test
    public void test() {
        System.out.println("------------");
    }

}
