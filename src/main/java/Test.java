import Concurrent.nio.Client;
import Concurrent.nio.Server;
import ProtectedAuthority.AnotherPackage.MyObject6;
import ProtectedAuthority.Test6;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Xu on 2017/3/23.
 */
public class Test {
    public static float RATE = 0.010f; //单抽出货率
    public static final int COUNT = 10; //抽十次

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            RATE += 0.001f;
            float result = buji(100000, true);
//            if(() > 1.2 && result < 1.25)
            System.out.println(RATE + ":" + result);
        }
    }

    /**
     *
     * @param count  十连次数
     * @param baodi  是否有保底机制
     * @return
     */
    public static float buji(int count, boolean baodi) {
        float totalRate = 0f;  //出货数
        int rate = (int) (RATE * 1000);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < COUNT; j++) {
                int rand = random.nextInt(1000);
                //前十发出货了
                if(rand <= rate)
                    totalRate += 1;
                else {
                    //第十发没出货的时候
                    if (baodi && j == COUNT - 1) {
                        totalRate += 1;
                    }
                }
            }
        }
        return totalRate / count;   //十连出货率
    }



}


