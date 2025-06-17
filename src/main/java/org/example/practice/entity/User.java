package org.example.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user_tab")
@Data  //set/get/toString()/无参构造器
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_username")
    private String username;

    @TableField("user_password")
    private String password;

    @TableField("user_name")
    private String name;

    @TableField("user_birth")
    private Date birth;

    @TableField("user_phone")
    private String phone;

    @TableField("user_avatar")
    private String avatar;

    @TableField("user_updateby")
    private String updateby;

    @TableField("user_update_time")
    private Date updateTime;


}

