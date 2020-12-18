package org.example.usera.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tangtian
 * @version 1.0
 * @className AssetLog
 * @description
 * @date 2020/12/18 7:19 AM
 **/
public class AssetLog {
    private static final Logger LOG = LoggerFactory.getLogger(AssetLog.class);

    private Integer id;

    private BigDecimal dollerAsset;

    private BigDecimal rmbAsset;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getDollerAsset() {
        return dollerAsset;
    }

    public void setDollerAsset(BigDecimal dollerAsset) {
        this.dollerAsset = dollerAsset;
    }

    public BigDecimal getRmbAsset() {
        return rmbAsset;
    }

    public void setRmbAsset(BigDecimal rmbAsset) {
        this.rmbAsset = rmbAsset;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
