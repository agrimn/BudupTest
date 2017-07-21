package com.project.olimpo.activityapp;

import android.app.Application;

import io.branch.referral.Branch;

/**
 * Created by anigam on 7/20/17.
 */
public class BudUpApp extends Application{

    public static String TAG = "Budup";
    public static String environment;
    public static String ENV_TEST = "test";

    public void onCreate() {
        super.onCreate();
        // initialize the Branch object
        Branch.getAutoInstance(this);
        Branch.enableLogging();
    }

}
