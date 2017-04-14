package me.donnie.github.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * @author donnieSky
 * @created_at 2017/2/23.
 * @description 网络相关工具类
 */
public class NetWorkUtil {

    private final Context context;

    public NetWorkUtil(@NonNull final Context context) {
        this.context = context;
    }

    public enum NetworkType {
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    /**
     * 打开网络设置页面
     */
    public void openWirelessSettings() {
        if (Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } else {
            context.startActivity(new Intent(Settings.ACTION_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    /**
     * 获取活动网络信息
     */
    private NetworkInfo getActiveNetWorkInfo() {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }

    /**
     * 判断网络是否连接
     */
    public boolean isConnected() {
        NetworkInfo info = getActiveNetWorkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 判断移动数据是否打开
     */
    public boolean isPhoneDataEnabled() {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method getDataEnabled = tm.getClass().getDeclaredMethod("getDataEnabled");
            if (getDataEnabled != null) {
                return (boolean) getDataEnabled.invoke(tm);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断网络是否是4G
     */
    public boolean is4G() {
        NetworkInfo info = getActiveNetWorkInfo();
        return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }

    /**
     * 判断WiFi是否打开
     * @return
     */
    public boolean isWifiEnabled() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    /**
     * 打开或关闭wifi
     */
    public void setWifiEnabled(boolean enabled) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (enabled) {
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
        } else {
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
        }
    }

    /**
     * 判断wifi是否连接
     */
    public boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

}
