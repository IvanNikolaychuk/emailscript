package com.service.db;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.*;

public class DateService {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH-mm-ss");

    public static long getDayId() {
        return new Date().getTime();
    }

    public static List<String> toReadableDates(List<Long> daysIds, List<Long> timeIds) {
        List<String> readableDates = new ArrayList<>(daysIds.size());

        Map<Calendar, List<Calendar>> dayToTimeListMap = mergeDays(daysIds, timeIds);

        for (Map.Entry<Calendar, List<Calendar>> entry : dayToTimeListMap.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(DATE_FORMAT.format(entry.getKey().getTime()))
                    .append(": ");

            for (Calendar time : entry.getValue()) {
                stringBuilder.append(TIME_FORMAT.format(time.getTime().getTime())).append("   ");
            }

            readableDates.add(stringBuilder.toString());
        }

        return readableDates;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(MINUTE, -20);

        List<Long> daysIds = Arrays.asList(new Date().getTime(), calendar.getTime().getTime());
        new DateService().toReadableDates(daysIds, daysIds);
    }

    /**
     * Collects time of them same day. Day acts as key, list of times as value.
     */
    private static Map<Calendar, List<Calendar>> mergeDays(List<Long> daysIds, List<Long> timeIds) {
        Map<Calendar, List<Calendar>> dayToTimeListMap = new HashMap<>();

        for (int i = 0; i < daysIds.size(); i++) {
            Calendar date = toDay(daysIds.get(i));
            List<Calendar> timeList = findWithSameDate(dayToTimeListMap, date);
            timeList.add(toTime(timeIds.get(i)));
            if (timeList.size() == 1) {
                dayToTimeListMap.put(date, timeList);
            }
        }

        return dayToTimeListMap;
    }

    private static List<Calendar> findWithSameDate(Map<Calendar, List<Calendar>> dayIdToTimeIdsMap, Calendar searchedDate) {
        for (Map.Entry<Calendar, List<Calendar>> entry : dayIdToTimeIdsMap.entrySet()) {
            Calendar actualDate = entry.getKey();
            if (actualDate.get(YEAR) == searchedDate.get(YEAR) &&
                    actualDate.get(MONTH) == searchedDate.get(MONTH) &&
                    actualDate.get(DAY_OF_MONTH) == searchedDate.get(DAY_OF_MONTH)) {
                return entry.getValue();
            }
        }

        return new ArrayList<>();
    }


    private static Calendar toTime(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar;
    }

    private static Calendar toDay(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(calendar.get(YEAR), calendar.get(MONTH), calendar.get(DAY_OF_MONTH), 0, 0, 0);

        return calendar;
    }
}
