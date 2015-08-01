package com.dailyschedule;

import java.util.Stack;
import android.app.Activity;
import android.app.Application;

public class DailyScheduleApplication extends Application {

	private Stack<Activity> activities;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		activities = new Stack<Activity>();
		System.out.println("app");
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

	}

	public void clearActivities() {
		for (Activity activity : activities) {
			activity.finish();
		}
		activities.clear();
	}
}
