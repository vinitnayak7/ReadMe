package com.lahacks.readme.spritz;


import com.spritzllc.android.sdk.SpritzClient;

/**
 * Created by avanha on 1/2/14.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SpritzClient.init(this,
                // SDK Examples Client ID
                "4524122930975538943",
                "b029cb59-efb7-4236-8367-11bd3e9f8ed1",
                "https://sdk.spritzinc.com/js/1.0/examples/login_success.html"
        );
    }
}
