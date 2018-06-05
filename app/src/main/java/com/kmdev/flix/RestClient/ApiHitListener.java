package com.kmdev.flix.RestClient;

/**
 * Created by ubuntu on 1/8/16.
 */

public interface ApiHitListener {
    void onSuccessResponse(int apiId, Object response);

    void onFailResponse(int apiId, String error);

    void networkNotAvailable();

}