import com.zdmoney.data.agent.api.facade.IDataAgentSafeFacadeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author laiwufa
 * @date 2019/4/9 0009 上午 9:41
 */
public class ThreadPoolTest {


    @org.junit.Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:threadPool.xmk");
    }

    public void threadPool(){

    }
}
