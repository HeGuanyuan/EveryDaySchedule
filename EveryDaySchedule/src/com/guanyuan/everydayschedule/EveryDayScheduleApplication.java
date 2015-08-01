package com.guanyuan.everydayschedule;

import java.util.Stack;

import android.app.Activity;
import android.app.Application;

public class EveryDayScheduleApplication extends Application {

	private Stack<Activity> activities;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initApplication();
	}

	private void initApplication() {
		
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

	public void addActivity(Activity a) {
		activities.add(a);
		for (Activity activity : activities) {
			activity.finish();
		}
		activities.clear();
	}

	public void clearActivities() {

	}
}
