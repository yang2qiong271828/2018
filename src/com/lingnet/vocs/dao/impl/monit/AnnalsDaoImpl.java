package com.lingnet.vocs.dao.impl.monit;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.util.Pager;
import com.lingnet.vocs.dao.monit.AnnalsDao;
import com.lingnet.vocs.entity.Annals;

@Repository("annalsDao")
public class AnnalsDaoImpl extends BaseDaoImplInit<Annals, String> 
implements AnnalsDao{

    @Override
    public Pager getListData(Pager pager,String eqCode,String name,String startdate,String enddate) {
        StringBuffer sql = new StringBuffer();
        Calendar  calendar= Calendar.getInstance();
        String tablename = "constant_data_";
        int month = calendar.get(Calendar.MONTH)+1;
        if(month<10){
            tablename = tablename + calendar.get(Calendar.YEAR)+"0"+(calendar.get(Calendar.MONTH)+1);
        }else{
            tablename = tablename + calendar.get(Calendar.YEAR)+(calendar.get(Calendar.MONTH)+1);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sdf.format(new Date());
        sql.append(" SELECT ");
        sql.append(" name,value,time_stamp,quality,flag ");
        sql.append(" FROM V_Constant  ");
        sql.append(" where fbox_uid='" + eqCode + "' ");
        sql.append(" and name='" + name + "' ");
        if(StringUtils.isNotEmpty(startdate)){
            sql.append(" and createdate >=   '"+startdate+" 00:00:00'" );
        }
        if(StringUtils.isNotEmpty(enddate)){
            sql.append(" and createdate <=   '"+enddate+" 23:59:59'" );
        }
        if(StringUtils.isEmpty(enddate) && StringUtils.isEmpty(startdate)){
            sql.append(" and createdate >=   '"+newDate+" 00:00:00'" );
        }
        sql.append(" order by time_stamp desc ");
        pager = this.findPagerBySql(pager, sql.toString());
       // SQLQuery sqlQuery = this.getSession().createSQLQuery(sql.toString());
        // 进行数据查询
       // List<Object[]> resultList = sqlQuery.list();
        return pager;
    }

    @Override
    public List<Object[]> getTemperatureChart(String eqCode, String uid,String startdate,String enddate) {
        StringBuffer sql = new StringBuffer();
        Calendar  calendar= Calendar.getInstance();
        String tablename = "constant_data_";
        int month = calendar.get(Calendar.MONTH)+1;
        if(month<10){
            tablename = tablename + calendar.get(Calendar.YEAR)+"0"+(calendar.get(Calendar.MONTH)+1);
        }else{
            tablename = tablename + calendar.get(Calendar.YEAR)+(calendar.get(Calendar.MONTH)+1);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sdf.format(new Date());
        sql.append(" SELECT ");
        sql.append(" name,value,time_stamp,quality,flag ");
        sql.append(" FROM V_Constant  ");
        //sql.append(" FROM "+tablename+"  ");
        sql.append(" where fbox_uid='" + eqCode + "' ");
        sql.append(" and name='" + uid + "' ");
        if(StringUtils.isNotEmpty(startdate)){
            sql.append(" and createdate >=   '"+startdate+" 00:00:00'" );
        }
        if(StringUtils.isNotEmpty(enddate)){
            sql.append(" and createdate <=   '"+enddate+" 23:59:59'" );
        }
        if(StringUtils.isEmpty(enddate) && StringUtils.isEmpty(startdate)){
            sql.append(" and createdate >=   '"+newDate+" 00:00:00'" );
        }
        
        sql.append(" order by time_stamp desc ");
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql.toString());
        // 进行数据查询
        List<Object[]> resultList = sqlQuery.list();
        return resultList;
    }
}
