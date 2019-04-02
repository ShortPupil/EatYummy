package com.songzi.yummy.util.map;

public class TestMap {

    public static String key="310e4085efc26b34dca89cf0a1131192";
    public static void main(String[] args) {
        String origin="104.07,30.67";//出发点经纬度
        String destination="104.46,29.23";//目的地经纬度
        //String key="310e4085efc26b34dca89cf0a1131192";//高德用户key

        MapNavUtil mapResult=new MapNavUtil(origin, destination, key);

        System.out.println(mapResult.getResults().toString());
    }
}
