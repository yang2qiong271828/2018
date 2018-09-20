package com.lingnet.vocs.action.atlas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometConnection;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.comet4j.core.event.ConnectEvent;
import org.comet4j.core.listener.ConnectListener;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.util.SpringUtil;
import com.lingnet.vocs.entity.AbnormalAlarm;
import com.lingnet.vocs.service.alarm.AbnormalAlarmService;

public class NewMsgCollector extends ConnectListener implements ServletContextListener {  
  
    private static final String CHANNEL = "hello";  
    
    @Override  
    public void contextInitialized(ServletContextEvent contextEvent) {  
        //注册应用的channel  
        CometContext context = CometContext.getInstance();  
        context.registChannel(CHANNEL);  
          
        //添加监听器  
        CometEngine engine = CometContext.getInstance().getEngine();  
        engine.addConnectListener(this);  
    }  
      
    @Override  
    public void contextDestroyed(ServletContextEvent contextEvent) {}  
  
    @Override  
    public boolean handleEvent(ConnectEvent connEvent) {  
        final CometConnection conn = connEvent.getConn();  
          
        //建立连接和用户的关系  
        doCache(conn);  
  
        final String connId = conn.getId();  
        /*模拟业务逻辑*/  
        Timer timer = new Timer(true);  
        TimerTask task = new TimerTask() {  
            @Override  
            public void run() {  
                CometEngine engine = CometContext.getInstance().getEngine();  
                //推送到所有客户端  
                //engine.sendToAll("hello", connId + " - you have " + ((int)(Math.random() * 9) + 1) + " new message <br />");  
                if (CacheManager.getContent(connId).isExpired()) {  
                    doCache(conn);  
                    AbnormalAlarmService service= (AbnormalAlarmService) SpringUtil.getBean("abnormalAlarmService");
                    List<AbnormalAlarm> abnormalAlarm = service.getOrderList(AbnormalAlarm.class,Order.desc("createDate"), Restrictions.eq("processingState", "0"));
                        //推送到指定的客户端  
                    if(abnormalAlarm != null&&abnormalAlarm.size()>0){
                    	if(abnormalAlarm.get(0)!=null){
                    		engine.sendTo(CHANNEL, engine.getConnection(connId), abnormalAlarm.get(0).getId());
                    	}
                    }
                      
                }
                
            }  
        };  
        timer.schedule(task, 0, (60*1000));  
        return true;  
    }  
  
    private void doCache(final CometConnection conn) {
    	ClassLoader cld = Thread.currentThread().getContextClassLoader();
    	String time = "";
    	 String path = cld.getResource("doc_config.properties").getPath();
    	 path = path.replaceAll("%20", " ");
         InputStream io = null;
         try {
			io = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
         Properties pro = new Properties();
         try {
			pro.load(io);
		} catch (IOException e) {
			e.printStackTrace();
		}
         time = pro.getProperty("time");
        CacheManager.putContent(conn.getId(), "", Integer.valueOf(time));  
    }  
  
    /** 
     * 废弃
     * 模拟业务 
     * 返回true,false 
     * true即表示需要推送消息,false即不需要推送 
     */  
    @SuppressWarnings("unused")
	private boolean simulateService(String id) {  
        return true;  
    }  
}  