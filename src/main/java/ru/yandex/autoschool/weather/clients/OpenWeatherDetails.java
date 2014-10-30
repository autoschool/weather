package ru.yandex.autoschool.weather.clients;

import com.google.gson.annotations.SerializedName;

/**
 * eroshenkoam
 * 29/10/14
 */
public class OpenWeatherDetails {

    @SerializedName("main")
    private OpenWeatherStatus status;

    @SerializedName("name")
    private String city;

    public OpenWeatherStatus getStatus() {
        return status;
    }

    public void setStatus(OpenWeatherStatus status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }
}
