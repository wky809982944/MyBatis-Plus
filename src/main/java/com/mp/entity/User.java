package com.mp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {
    private static final long serialVersionUID = 1L;
    //    主键
    @TableId
    private Long id;
    //    姓名
    private String name;
    //    年龄
    private Integer age;
    //    邮箱
    private String email;
    //    直属上级
    private Long managerId;
//    创建时间
    private LocalDateTime createTime;


}
