package com.songzi.yummy.util.map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.util.HttpRequest;

/**
 * 高德地图导航工具类
 */
public class MapNavUtil {
    private String startCoordinate;
    private String endCoordinate;
    private String applicationKey;
    private String param;
    /**
     * 必须要构造参数
     * @param startCoordinate 起点经纬度 经度在前，纬度在后
     * @param endCoordinate 终点经纬度 经度在前，纬度在后
     * @param applicationKey 高德地图应用key，需要Web服务类型的key
     */
    public MapNavUtil(String startCoordinate, String endCoordinate,
                      String applicationKey) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.applicationKey = applicationKey;
        this.param="origin="+this.startCoordinate+"&destination="+this.endCoordinate+"&key="+this.applicationKey;
    }
    /**
     * 获取地图导航返回值
     * @return
     */
    public MapNavResults getResults(){
        String sendGet = HttpRequest.sendGet("https://restapi.amap.com/v4/direction/bicycling", param);
        JSONObject jsonObject=JSONObject.parseObject(sendGet);
        System.out.println(jsonObject);

        String routeJsonString = jsonObject.get("data").toString();
        JSONObject routeObject=JSONObject.parseObject(routeJsonString);
        JSONArray jsonArray = routeObject.getJSONArray("paths");
        JSONObject zuiJson = jsonArray.getJSONObject(0);
        MapNavResults mapResult=new MapNavResults();
        mapResult.setDistance(zuiJson.get("distance").toString());
        mapResult.setDuration(zuiJson.get("duration").toString());
        //mapResult.setTolls(zuiJson.get("polyline").toString());
        //System.out.println(zuiJson.toString());
        return mapResult;
    }


}

