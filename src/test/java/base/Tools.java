package base;

import org.apache.log4j.Logger;
import utils.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: myAppium
 * @description:
 * @author: bjwuzh
 * @create: 2020-08-15 11:17
 **/
public class Tools {
    /**
     * 秒等
     * @param sec
     */
    public static void wait(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取安卓的uid，如果连接多台设备，则随机抽取一个
     * @return
     */
    public static String getAndroidDeviceId() {
        String androidId = getRandomAndroidDeviceID();
        Log.info("Android device uid: " + androidId);
        return androidId;
    }
    /**
     * 获取对应安卓的release版本
     * @param uid
     * @return
     */
    public static String getDeviceRelease(String uid){
        String deviceRelease = "";
        List<String> al = runExec("adb -s " + uid + " shell getprop ro.build.version.release");
        if(al != null && al.size()>0){
            deviceRelease = al.get(0);
        }
        Log.info("Android release versio " + deviceRelease + "\n");
        return deviceRelease;

    }
    /**
     * 卸载apk
     * @param uid
     * @return
     */
    public static void uninstallPackage(String uid) {
        List<String> al = runExec("adb -s " + uid + " uninstall com.example.android.contactmanager");
        Log.info("Clean the contact manager app.");
    }
    /**
     * 获取随机安卓uid
     * @return
     */
    private static String getRandomAndroidDeviceID() {
        String  id = "";
        List<String> al = getAndroidDeviceIds();
        if ( null != al && al.size() > 0 ){
            int i = new Random().nextInt(al.size());
            id = al.get(i);
        }
        return id;
    }

    /**
     * 获取安卓uid列表
     * @return
     */
    private static List<String> getAndroidDeviceIds() {
        ArrayList<String> deviceIds = new ArrayList<String>();
        List<String> al = runExec("adb devices");
        if ( null != al ) {
            for ( int i = 1; i < al.size(); i++ ){
                String tmpStr = al.get(i);
                if ( null != tmpStr && tmpStr.contains("device")) {
                    tmpStr = tmpStr.replace("device","").trim();
                    deviceIds.add(tmpStr);
                }
            }
        }
        return deviceIds;
    }
    /**
     * 运行命令行
     * @param cmd
     * @return
     */
    private static List<String> runExec(String cmd) {
        ArrayList<String> output = new ArrayList<String>();
        try {
            Log.debug("cmd = '"+ cmd +"'");
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            InputStream stdin = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line = null;
            Log.debug("This is java runExec output");
            Log.debug("<OUTPUT>");
            while ((line = bufferedReader.readLine()) != null) {
                output.add(line);
                Log.debug(line);
            }
            Log.debug("</OUTPUT>");
            int exitVal = proc.waitFor();
            Log.debug("Process exitvalue： " + exitVal);
        }catch (Throwable t) {
            t.printStackTrace();
        }
        return output;
    }
}