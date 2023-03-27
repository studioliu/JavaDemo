package com.studio.new_datatime_api;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * java8-新时间日期API（都在java.time包下）
 * java.time            针对时间日期操作
 * java.time.chrono     处理时间日期特殊格式
 * java.time.format     时间日期格式化
 * java.time.temporal   时间校正器，对时间日期做运算
 * java.time.zone       対时区做运算
 * 以前的时间日期API都是线程不安全的，
 * 新的时间日期API是线程安全的，是不可变的，每一操作都产生一个新的实例
 */
public class TestNewDateTimeAPI {

    // 1.LocalDate LocalTime LocalDateTime
    @Test
    public void test1() {
        // 系统时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        // 指定时间
        LocalDateTime ldt2 = LocalDateTime.of(2020, 10, 1, 12, 16, 12);
        System.out.println(ldt2);

        // 加
        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        // 减
        LocalDateTime ldt4 = ldt.minusMonths(2);
        System.out.println(ldt4);

        // get方法
        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    // 2.Instant ：时间戳（以 Unix 元年：1970年1月1日 00:00:00到某个时间之间的毫秒值）
    @Test
    public void test2() {
        Instant ins1 = Instant.now();   // 默认获取的是 UTC 时区
        System.out.println(ins1);

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));  // 修改时差（与北京差八个小时）
        System.out.println(odt);

        System.out.println(odt.toEpochSecond());    // 转为时间戳

        Instant ins2 = Instant.ofEpochSecond(6);
        System.out.println(ins2);
    }

    // Duration ：计算两个“时间”之间的间隔
    // Period ：计算两个“日期”之间的间隔
    @Test
    public void test3() throws InterruptedException {
        Instant ins1 = Instant.now();
        Thread.sleep(1000);
        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.toMillis());

        System.out.println("---------------------------------------");

        LocalDateTime ldt1 = LocalDateTime.now();
        Thread.sleep(1000);
        LocalDateTime ldt2 = LocalDateTime.now();

        System.out.println(Duration.between(ldt1, ldt2).toMillis());
    }

    @Test
    public void test4() {
        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2025, 6, 6);

        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println("两日期间隔" + period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "日");
    }


    @Test
    public void test5() {
        List<String> dateTimeList = new ArrayList<>();
        dateTimeList.add("2022-06-01 06:26:00");
        dateTimeList.add("2022-08-24 15:59:18");
        dateTimeList.add("2020-10-16 12:17:33");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (String s : dateTimeList) {
            LocalDateTime ldt = LocalDateTime.parse(s, dtf);
            System.out.println(s);
            System.out.println(ldt);
            System.out.println("-------------------------");
        }
    }

    @Test
    public void test6() {
        // 时间戳 转 时间日期
        long timestamp = System.currentTimeMillis();
        LocalDateTime ldt1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        System.out.println(timestamp);
        System.out.println(ldt1);

        System.out.println("-------------------------------");

        // 时间日期 转 时间戳
        String dataTime = "2022-11-02 21:16:00";
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dataTime, dtf2);
//        long ts = ldt.toInstant(ZoneOffset.UTC).toEpochMilli();   //这里在什么时区就用什么时区解释时间戳，北京时间=UTC + 8:00
        long ts = ldt.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(dataTime);
        System.out.println(ts);
    }

    // TemporalAdjuster ：时间校正器
    @Test
    public void test7() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        // TemporalAdjusters工具类
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));    //下一个周日
        System.out.println(ldt3);

        // 自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dow = ldt4.getDayOfWeek();
            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    // DateTimeFormatter ：格式化时间/日期
    @Test
    public void test8(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        System.out.println("---------------------------------------");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        LocalDateTime newDate = ldt.parse(strDate2, dtf2);
        System.out.println(newDate);
    }

    // ZonedDate、ZonedTime、ZonedDateTime：时区操作
    @Test
    public void test9(){
        // 支持的所有时区
//        Set<String> set = ZoneId.getAvailableZoneIds();
//        set.forEach(System.out::println);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));    //带有时区的日期时间
        System.out.println(zdt);
    }

}
