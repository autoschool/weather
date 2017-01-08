package ru.yandex.autoschool.weather.clients;

/**
 * eroshenkoam
 * 29/10/14
 */
public interface OpenWeatherClient {

    String APP_ID = "8d59609d9f7710500ed92e7a199c2d14";

    //    @GET("/data/2.5/weather")
    OpenWeatherResponse getWeather(
//            @Query("q")
            String query,
//            @Query("APPID")
            String appId);

    static OpenWeatherClient getOpenWeatherService() {
//        RestAdapter adapter = new RestAdapter.Builder()
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .setLog(LOG::info)
//                .setEndpoint("http://api.openweathermap.org")
//                .build();
        return null;
    }
}

