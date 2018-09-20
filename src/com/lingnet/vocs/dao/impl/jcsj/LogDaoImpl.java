package com.lingnet.vocs.dao.impl.jcsj;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.entity.QxLog;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.jcsj.LogDao;

/**
 * 
 * @ClassName: LogDaoImpl 
 * @Description: 日志管理dao实现类 
 * @author 姜平豹
 * @date 2014-3-26 上午10:06:38 
 *
 */
@Repository("logDao")
public class LogDaoImpl extends BaseDaoImplInit<QxLog, String> implements
LogDao {
  //获得采购收货主表列表分页
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<HashMap> search(Pager pager, HashMap<String, String> searchMap) {
        DateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        List<HashMap> mapList = new ArrayList<HashMap>();
        String czUser = searchMap.get("czUser");
        String stadate = searchMap.get("stadate");
        String enddate = searchMap.get("enddate");
        String czType = searchMap.get("czType");
        String djType = searchMap.get("djType");
        String ip = searchMap.get("ip");
        
        StringBuilder sql = new StringBuilder();
        //添加数据库类型的判断  栾胜鹏 2014-10-14
        /**
         * mysql DATE_FORMAT '%Y-%m-%d %H:%i:%S' 相当于 YYYY-MM—DD HH:mm:ss
         */
        String datapro = (String) ServletActionContext.getContext().getSession().get("dataPro");
        
        if(datapro.equals("oracle")){
            sql.append("select log.id, log.CZ_USER,to_char(log.CZ_DATE,'yyyy-mm-dd hh24:mi:ss'),log.DJ_TYPE,log.CZ_TYPE,log.CZ_OBJECT," +
                       "log.IP_DZ, log.PC_NAME,log.RMK,log.czdj");
            
            sql.append("  from QX_LOG log");
            
            sql.append(" where 1=1 ");
            
            if(stadate!=null&& !"".equals(stadate)){
                if(enddate!=null&& !"".equals(enddate)){
                    sql.append("       and to_char(log.cz_Date,'YYYY-MM-DD HH:mm:ss')  >= '" + stadate + " 00:00:0' and to_char(log.cz_Date,'YYYY-MM-DD  HH:mm:ss') <= '" + enddate + " 00:00:0'");
                }else{
                    sql.append("       and to_char(log.cz_Date,'YYYY-MM-DD HH:mm:ss')  >= '" + stadate + " 00:00:0' and to_char(log.cz_Date,'YYYY-MM-DD  HH:mm:ss') <= '" + from.format(new Date())+"'" );
                }
            }else{
                if(enddate!=null&& !"".equals(enddate)){
                    sql.append("       and to_char(log.cz_Date,'YYYY-MM-DD HH:mm:ss')  >= '" + "2007-01-01" + " 00:00:0' and to_char(log.cz_Date,'YYYY-MM-DD  HH:mm:ss') <= '" + enddate + " 00:00:0'");
                }else{
                    sql.append("       and to_char(log.cz_Date,'YYYY-MM-DD HH:mm:ss')  >= '" + "2007-01-01" + " 00:00:0' and to_char(log.cz_Date,'YYYY-MM-DD  HH:mm:ss') <= '" + from.format(new Date())+"'");
                }
            }
            
            if (czUser != null && !"".equals(czUser)) {
                sql.append("       and log.cz_User like \'%"+czUser+"%\'");
            }
            if (czType != null && !"".equals(czType)) {
                sql.append("       and log.cz_Type in("+czType+")");
            }
            if (djType != null && !"".equals(djType)) {
                sql.append("       and log.dj_Type like \'%"+djType+"%\'");
            }
            if (ip != null && !"".equals(ip)) {
                sql.append("       and log.IP_DZ like \'%"+ip+"%\'");
            }
            sql.append(" order by log.createdate desc");
              
        }else  if(datapro.equals("msdn")){
        	sql.append("select log.id, log.CZ_USER, CONVERT(varchar(19),log.CZ_DATE,121),log.DJ_TYPE,log.CZ_TYPE,log.CZ_OBJECT," +
                    "log.IP_DZ, log.PC_NAME,log.RMK,log.czdj");
         
	         sql.append("  from QX_LOG log");
	         
	         sql.append(" where 1=1 ");
	         if(stadate!=null&& !"".equals(stadate)){
	             if(enddate!=null&& !"".equals(enddate)){
	                 sql.append("       and CONVERT(varchar(17),log.cz_DATE,121)  >= '" + stadate + " 00:00:0' and CONVERT(varchar(17),log.cz_DATE,121) <= '" + enddate + " 23:59:59'");
	             }else{
	                 sql.append("       and CONVERT(varchar(17),log.cz_DATE,121)  >= '" + stadate + " 00:00:0' and CONVERT(varchar(17),log.cz_DATE,121) <= '" + from.format(new Date())+"'" );
	             }
	         }else{
	             if(enddate!=null&& !"".equals(enddate)){
	                 sql.append("       and CONVERT(varchar(17),log.cz_DATE,121) >= '" + "2017-06-01" + " 00:00:0' and CONVERT(varchar(17),log.cz_DATE,121) <= '" + enddate + " 23:59:59'");
	             }else{
	                 sql.append("       and CONVERT(varchar(17),log.cz_DATE,121)  >= '" + "2017-06-01" + " 00:00:0' and CONVERT(varchar(17),log.cz_DATE,121) <= '" + from.format(new Date())+"'");
	             }
	         }
	         if (czUser != null && !"".equals(czUser)) {
	             sql.append("       and log.cz_User like \'%"+czUser+"%\'");
	         }
	         if (czType != null && !"".equals(czType)) {
	             sql.append("       and log.cz_Type in("+czType+")");
	         }
	         if (djType != null && !"".equals(djType)) {
	             sql.append("       and log.dj_Type like \'%"+djType+"%\'");
	         }
	         if (ip != null && !"".equals(ip)) {
	             sql.append("       and log.IP_DZ like \'%"+ip+"%\'");
	         }
	         sql.append(" order by log.createdate desc");
       }else{
            sql.append("select log.id, log.CZ_USER,DATE_FORMAT(log.CZ_DATE,'%Y-%m-%d %H:%i:%S'),log.DJ_TYPE,log.CZ_TYPE,log.CZ_OBJECT," +
                       "log.IP_DZ, log.PC_NAME,log.RMK,log.czdj");
            
            sql.append("  from QX_LOG log");
            
            sql.append(" where 1=1 ");
            if(stadate!=null&& !"".equals(stadate)){
                if(enddate!=null&& !"".equals(enddate)){
                    sql.append("       and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S')  >= '" + stadate + " 00:00:0' and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S') <= '" + enddate + " 23:59:59'");
                }else{
                    sql.append("       and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S')  >= '" + stadate + " 00:00:0' and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S') <= '" + from.format(new Date())+"'" );
                }
            }else{
                if(enddate!=null&& !"".equals(enddate)){
                    sql.append("       and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S')  >= '" + "2007-01-01" + " 00:00:0' and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S') <= '" + enddate + " 23:59:59'");
                }else{
                    sql.append("       and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S')  >= '" + "2007-01-01" + " 00:00:0' and DATE_FORMAT(log.cz_Date,'%Y-%m-%d %H:%i:%S') <= '" + from.format(new Date())+"'");
                }
            }
            if (czUser != null && !"".equals(czUser)) {
                sql.append("       and log.cz_User like \'%"+czUser+"%\'");
            }
            if (czType != null && !"".equals(czType)) {
                sql.append("       and log.cz_Type in("+czType+")");
            }
            if (djType != null && !"".equals(djType)) {
                sql.append("       and log.dj_Type like \'%"+djType+"%\'");
            }
            if (ip != null && !"".equals(ip)) {
                sql.append("       and log.IP_DZ like \'%"+ip+"%\'");
            }
            sql.append(" order by log.createdate desc");
        }
        
        try{
            SQLQuery query=this.getSession().createSQLQuery(sql.toString());
            
            List<Object[]>  resultList = query.list();
            pager.setResult(resultList);
            pager.setTotalCount(resultList.size());
            if(resultList != null){
                int i=(pager.getPageNumber()-1)*pager.getPageSize();
                int dqsl;
                if((resultList.size()-pager.getPageNumber()*pager.getPageSize())>0){
                    dqsl=pager.getPageNumber()*pager.getPageSize();
                }else{
                    dqsl=resultList.size();
                }
                for(;i<dqsl;i++){
                    HashMap hashmap=new HashMap();
                    
                    hashmap.put("id", resultList.get(i)[0]);
                    hashmap.put("zcUser", resultList.get(i)[1]);
                    hashmap.put("czDate", resultList.get(i)[2]);
                    hashmap.put("djType", resultList.get(i)[3]);
                    hashmap.put("czType", resultList.get(i)[4]);
                    hashmap.put("czObject", resultList.get(i)[5]);
                    hashmap.put("ipDz", resultList.get(i)[6]);
                    hashmap.put("pcName", resultList.get(i)[7]);
                    hashmap.put("rmk", resultList.get(i)[8]);
                    hashmap.put("czdj", resultList.get(i)[9]);
                    
                    mapList.add(hashmap);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mapList;
    }

    @Override
    public String Operate(String djType, String czType) {
        InetAddress addr;
        String ip="";
        String address="";
        try {
            addr = InetAddress.getLocalHost();
            ip=addr.getHostAddress().toString();//获得本机IP
            address=addr.getHostName().toString();//获得本机名称
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       

        QxLog log=new QxLog();
        log.setCzUser(LingUtil.userName());
        log.setCzDate(new Date());
        log.setDjType(djType);
        log.setCzType(czType);
        log.setIpDz(ip);
        log.setPcName(address);
        return null;
    }
    

}
