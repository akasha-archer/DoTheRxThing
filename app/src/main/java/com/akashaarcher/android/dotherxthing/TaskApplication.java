package com.akashaarcher.android.dotherxthing;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Created by akashaarcher on 9/20/17.
 */

public class TaskApplication extends Application {

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

}
