package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
@Table(name = "yx_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Excel(name = "ID",height = 20,width = 20)
    private String id;
    @Excel(name = "姓名",height = 20,width = 20)
    private String username;
    @Excel(name = "手机号",height = 20,width = 20)
    private String phone;
    @Column(name="head_img")

    private String headImg;
    @Excel(name = "签名",height = 20,width = 20)
    private String sign;
    @Excel(name = "微信",height = 20,width = 20)
    private String wechat;
    @Excel(name = "状态",height = 20,width = 20)
    private String status;
    @Excel(name = "性别",height = 20,width = 20)
    private String sex;
    @Excel(name = "城市",height = 20,width = 20)
    private String city;
    @Column(name="creat_date")
    @Excel(name = "日期",format = "yyyy-MM-dd")
    private Date creatDate;

    @Transient
    @Excel(name="头像",type = 2,imageType = 2)
    private byte[] image;

}