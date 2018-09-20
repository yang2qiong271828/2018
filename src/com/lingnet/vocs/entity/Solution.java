package com.lingnet.vocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 问题解决方案
 * @ClassName: Solution 
 * @Description: TODO 
 * @author tangjw
 * @date 2017年6月2日 上午10:32:55 
 *
 */
@Entity
@Table(name = "solution")
public class Solution extends BaseEntity {
    
    private String questionId;//问题编号
    private String method;//解决方案
    private Date solveTime;//解决时间
    private String useTime;//解决用时
    
    @Column(name = "question_id")
    public String getQuestionId() {
        return questionId;
    }
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    
    @Column(name = "method")
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    
    @Column(name = "solve_time")
    public Date getSolveTime() {
        return solveTime;
    }
    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }
    
    @Column(name = "use_time")
    public String getUseTime() {
        return useTime;
    }
    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
    
    

}
