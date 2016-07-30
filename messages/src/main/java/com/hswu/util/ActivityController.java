package com.hswu.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HandsomeWu on 2016/7/31.
 */

public class ActivityController {

    private static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity)
    {
        if (activities.contains(activity))
        {
            return;
        }
        activities.add(activity);
    }

    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public  static void finishAll()
    {
        for (Activity activity:activities) {

            if (!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }
}
