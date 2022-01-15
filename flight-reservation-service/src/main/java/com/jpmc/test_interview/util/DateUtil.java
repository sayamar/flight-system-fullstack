package com.jpmc.test_interview.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class DateUtil {

    private static final SimpleDateFormat
       simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public String convertStringToDate(String dateInString){
        try {
            Date parsedDate = simpleDateFormatter.parse(dateInString);
            return simpleDateFormatter.format(parsedDate);
   } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertTimestampToDate(Timestamp timestamp){
            log.info("Departure data in memory "+simpleDateFormatter.format(new Date(timestamp.getTime())));
            return simpleDateFormatter.format(new Date(timestamp.getTime()));

    }

//    public static void main(String[] args) {
//        DateUtil util= new DateUtil();
//        String d1 = util.convertStringToDate("14-01-2022");
//        String d2 = util.convertTimestampToDate(new Timestamp(System.currentTimeMillis()));
//        System.out.println(d1+" "+d2);
//       System.out.println(d1.equals(d2));
//
//    }

}
