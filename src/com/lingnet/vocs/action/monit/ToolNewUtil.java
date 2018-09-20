package com.lingnet.vocs.action.monit;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.vocs.service.baseinfo.DataDictionaryDService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("toolNewUtil")
public class ToolNewUtil {
    @Resource(
            name = "dataDictionaryDService"
    )
    public DataDictionaryDService dataDicDService;
    @Resource(
            name = "adminService"
    )
    public AdminService adminService;
    public HashMap<Integer, String> adsZCRMap = new HashMap();


    public ToolNewUtil() {
    }

    public static String userName() {
        Object oPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails)oPrincipal).getUsername();
        return userName;
    }

    public String getUserId() {
        String userName = userName();
        QxUsers user = this.adminService.getUserByName(userName);
        String userId = "";
        if (user != null) {
            userId = user.getId();
        }

        return userId;
    }

    public String getUserDeptId() {
        String deptId = "";
        String userName = userName();
        QxUsers user = this.adminService.getUserByName(userName);
        if (user != null) {
            deptId = user.getDepId();
        }

        return deptId;
    }

    public static String getPropert(String property, String name) {
        String url = "";

        try {
            ClassLoader cls = Thread.currentThread().getContextClassLoader();
            InputStream in = cls.getResourceAsStream(property);
            Properties p = new Properties();
            p.load(in);
            if (p.containsKey(name)) {
                url = p.getProperty(name);
            }
        } catch (IOException var6) {
            ;
        }

        return url;
    }

    public static Long timedif(String time1, String time2) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        Date now = df.parse(time1);
        Date date = df.parse(time2);
        long l = now.getTime() - date.getTime();
        return l;
    }

    public final QxUsers getCurrentUser() {
        String username = userName();
        return this.adminService.getUserByName(username);
    }

    public String getDataDicByCodeAndVal(String code, String value) {
        String name = this.dataDicDService.getNameByCodeAndValue(code, value);
        return name;
    }

    public static Map<String, BigDecimal> getLatAndLngByAddress(String addr) {
        String address = "";
        String lat = "";
        String lng = "";

        try {
            address = URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException var12) {
            var12.printStackTrace();
        }

        String url = String.format("http://api.map.baidu.com/geocoder/v2/?ak=j1kyzAdFny4dEBl3ULTMqm6B&output=json&address=%s", address);
        URL myURL = null;
        URLConnection httpsConn = null;

        try {
            myURL = new URL(url);
        } catch (MalformedURLException var11) {
            ;
        }

        try {
            httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    lat = data.substring(data.indexOf("\"lat\":") + "\"lat\":".length(), data.indexOf("},\"precise\""));
                    lng = data.substring(data.indexOf("\"lng\":") + "\"lng\":".length(), data.indexOf(",\"lat\""));
                }

                insr.close();
            }
        } catch (IOException var10) {
            ;
        }

        Map<String, BigDecimal> map = new HashMap();
        map.put("lat", new BigDecimal(lat));
        map.put("lng", new BigDecimal(lng));
        return map;
    }

    @PostConstruct
    public void init() {
        AdsZCRVarName[] var4;
        int var3 = (var4 = AdsZCRVarName.values()).length;

        int var2;
        for(var2 = 0; var2 < var3; ++var2) {
            AdsZCRVarName varName = var4[var2];
            this.adsZCRMap.put(varName.getIndex(), varName.getName());
        }
    }

    public HashMap<Integer, String> getAdsZCRMap() {
        return this.adsZCRMap;
    }

    public void setAdsZCRMap(HashMap<Integer, String> adsZCRMap) {
        this.adsZCRMap = adsZCRMap;
    }

}

