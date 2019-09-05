package com.tgcity.base.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TGCity
 * @date 2019/9/5
 * @describe getPermissionsResult utils
 */
public class PermissionUtils {

    /**
     * get permissions result
     * @param activity Activity
     * @return boolean
     */
    public static boolean getPermissionsResult(Activity activity) {
        boolean isPermitted = false;

        List<String> permissionLists = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.CAMERA);
        }

        if (!permissionLists.isEmpty()) {
            //说明肯定有拒绝的权限
            ActivityCompat.requestPermissions(activity, permissionLists.toArray(new String[permissionLists.size()]), 1);
        } else {
            isPermitted = true;
        }

        return isPermitted;
    }
}
