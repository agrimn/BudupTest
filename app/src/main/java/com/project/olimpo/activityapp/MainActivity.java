package com.project.olimpo.activityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Look for Authentication token
//        final FirebaseUser user = Authentication.getUser();
//        if (user == null) {
//            Log.d(BudUpApp.TAG,"[AUTH] User Null");
//            //Not authenticated on Firebase
//            //If authenticated in Facebook, invalidate to enable Facebook Login again
//            AccessToken accessToken = AccessToken.getCurrentAccessToken();
//            if (AccessToken.getCurrentAccessToken() != null) {
//                Log.d(BudUpApp.TAG,"[AUTH] Login with current credentials");
//                Authentication.handleFacebookAccessLogin(accessToken,this);
//            } else {
//                //We go to Login screen
//                Intent intent = new Intent(Initial.this, Login.class);
//                startActivity(intent);
//                finish();
//            }
//        } else {
//            Log.d(BudUpApp.TAG,"[AUTH] User Authenticated");
//            Data.initUserData();
//            Log.d(BudUpApp.TAG,"[AUTH] initUserData");
//            Data.getActivities();
//            Log.d(BudUpApp.TAG,"[AUTH] getActivities");
//            //Check for redirection from notifications
//            String type = getIntent().getStringExtra(FCMData.TYPE);
//            String activityId = getIntent().getStringExtra(ActivityDetail.ACTIVITY_ID);
//            debug("INITIAL","type = "+type);
//            debug("INITIAL","activityId = "+activityId);
//            if (FCMMessagingService.ACTIVITY_JOIN.equals(type) && activityId != null){
//                Intent intent = new Intent(Initial.this, ActivityDetail.class);
//                intent.putExtra(ActivityDetail.ACTIVITY_ID, activityId);
//                startActivity(intent);
//                finish();
//            } else  if (FCMMessagingService.ACTIVITY_MESSAGE.equals(type) && activityId != null){
//                Intent intent = new Intent(Initial.this, ChatActivity.class);
//                intent.putExtra(ActivityDetail.ACTIVITY_ID, activityId);
//                startActivity(intent);
//                finish();
//            } else  if (NotificationReminder.REMINDER.equals(type) && activityId != null){
//                Intent intent = new Intent(Initial.this, ActivityDetail.class);
//                intent.putExtra(ActivityDetail.ACTIVITY_ID, activityId);
//                startActivity(intent);
//                finish();
//            } else {
//                Log.d(BudUpApp.TAG,"[AUTH] updatingFacebookProfile");
//                Authentication.updateFacebookProfile();
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        /* CODE SNIPPET YOU SENT US EARLIER THAT WAS WORKING */
//        if (this.getIntent().getData() != null) Log.d("Example", "this.getIntent().getData():"+this.getIntent().getData().toString());
//        Branch.getInstance().initSession(new Branch.BranchUniversalReferralInitListener() {
//
//            @Override
//            public void onInitFinished(BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, BranchError branchError) {
//                //If not Launched by clicking Branch link
//                if (branchUniversalObject == null) {
//                    Log.d("Example", "BUO NULL");
//                    Toast.makeText(getApplicationContext(), "BUO IS NULL, NO DATA", Toast.LENGTH_LONG).show();
//                } else {
//                    Log.d("Example", "BUO NOT NULL:");
//                    Log.d("Example", branchUniversalObject.getMetadata().toString());
//                    Toast.makeText(getApplicationContext(), "BUO NOT NULL: " + branchUniversalObject.getMetadata().toString(), Toast.LENGTH_LONG).show();
//                }
//            }
//        }, this.getIntent().getData(), this);

        /* THE MOST RECENT CODE SNIPPET THAT YOU SENT US */
        Branch.getInstance().initSession(new Branch.BranchReferralInitListener() {

            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                //If not Launched by clicking Branch link
                if (error == null) {
                    if (referringParams != null) {
                        // This code will execute when your app is opened from a Branch deep link, which
                        // means that you can route to a custom activity depending on what they clicked.
                        // In this example, we'll just print out the data from the link that was clicked.

                        // check if the item is contained in the metadata
                        String startActivityId = referringParams.optString("activity_id",null);
                        String testEnvironment = referringParams.optString("test_environment","FALSE");
                        Log.d(BudUpApp.TAG, "[BRANCH] activityID: "+startActivityId);
                        Log.d(BudUpApp.TAG, "[BRANCH] testEnvironment: "+testEnvironment);
                        if ("TRUE".equals(testEnvironment)) BudUpApp.environment = BudUpApp.ENV_TEST;
                        if (startActivityId != null) {
                            Log.i(BudUpApp.TAG, "[BRANCH] Deep Link found");
//                            if (schemadata != null)
//                                Log.i(BudUpApp.TAG, "[BRANCH] Schema Data: " + schemadata.toString());

                            /*
                             * Instead of launching the Activity here we are showing a toast message
                             */
//                            startActivityFromReferral(user, startActivityId);
                            Toast.makeText(getApplicationContext(), "BUO NOT NULL: " + referringParams.toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Log.d(BudUpApp.TAG, "[BRANCH] Deep Link not found");
//                            backupReferralLink(user, schemadata);
                        }
                    } else {
                        Log.d(BudUpApp.TAG, "[BRANCH] Deep Link not found");
//                        backupReferralLink(user, schemadata);
                    }

                } else {
                    Log.d(BudUpApp.TAG, error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
}
