package com.zilanghuo.zk;

/**
 * @author laiwufa
 * @date 2019/2/25 0025 下午 2:47
 */
public class Caller {

    public MyCallInterface mc;

    public void setCallfuc(MyCallInterface mc)
    {
        this.mc= mc;
    }

    public void call(){
        this.mc.method();
    }
}
