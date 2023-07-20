package com.raghu.speaking.clock.service;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

import com.raghu.speaking.clock.util.Util;

@Service
public class ClockService {

    private static final String MID_DAY="It is MidDay";
    private static final String MID_NIGHT="It is MidNight";

    public String getConvertInEnglish(LocalTime time) {
        CompletableFuture<String> hoursString=CompletableFuture.supplyAsync(()->checkHours(time.getHour()));
        CompletableFuture<String> minutesString=CompletableFuture.supplyAsync(()->checkMinutes(time.getMinute()));

             return   hoursString.thenCombine(minutesString,(h,m)->{
                if(h==MID_DAY||h==MID_NIGHT) return h;
                return h+m;
            })
                .join();
    }

    static String checkHours(int hours){
        if(hours==00){
            return "It is twelve ";
        }else if(hours==11){
            return MID_DAY;
        }else if(hours == 23){
            return MID_NIGHT;
        }
        String str=Util.timeStore().get(hours);
        return "It is "+str+" ";
    }

    static String checkMinutes(int minutes){
        return Util.timeStore().get(minutes);
    }
}

