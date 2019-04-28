package com.zilanghuo.lcbtest.hession;

import com.caucho.hessian.client.HessianProxyFactory;
import com.zdmoney.integral.api.common.dto.ResultDto;
import com.zdmoney.integral.api.dto.lcbaccount.enm.AuthStatus;
import com.zdmoney.integral.api.dto.lcbaccount.enm.AuthType;
import com.zdmoney.integral.api.dto.lcbaccount.query.QueryUserInfoDto;
import com.zdmoney.integral.api.dto.lcbaccount.query.QueryUserInfoResultDto;
import com.zdmoney.integral.api.facade.IDepositFacadeService;

import java.net.MalformedURLException;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 下午 2:09
 */
public class HessionTest {

    public static void main(String[] args) {
        String url = "http://172.17.34.3:8081/integral/depositFacadeService";
        HessianProxyFactory factory = new HessianProxyFactory();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    IDepositFacadeService depositFacadeService = null;
                    try {
                        depositFacadeService = (IDepositFacadeService) factory.create(IDepositFacadeService.class, url);
                    } catch (MalformedURLException e) {

                    }
                    QueryUserInfoDto reqDto = new QueryUserInfoDto();
                    reqDto.setLoginId("18616329138");
                    ResultDto<QueryUserInfoResultDto> resultDto = depositFacadeService.queryUserInfo(reqDto);
                    if (resultDto.isSuccess()) {
                        QueryUserInfoResultDto data = resultDto.getData();
                        EnumMap<AuthType, AuthStatus> authStatus = data.getAuthStatus();
                        if (authStatus != null && !authStatus.isEmpty()) {
                            Iterator<Map.Entry<AuthType, AuthStatus>> it = authStatus.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<AuthType, AuthStatus> entry = it.next();
                                entry.getKey().hashCode();
                                System.out.println(finalI + "authstatus enummap entry:{}-{}===={}-{}" + (entry.getKey() != null ? entry.getKey().name() : "") + (entry.getKey() != null ? entry.getKey().ordinal() : -1) + (entry.getValue() != null ? entry.getValue().name() : "") + (entry.getValue() != null ? entry.getValue().ordinal() : -1));
                            }
                        } else {
                            System.out.println("authstatus enummap is null or empty");
                        }

                    }
                }
            });
        }
    }

}
