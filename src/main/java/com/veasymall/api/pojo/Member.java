package com.veasymall.api.pojo;

import java.util.Date;
import javax.persistence.*;

public class Member {
    @Id
    private Integer id;

    /**
     * 会员昵称
     */
    private String nickname;

    /**
     * 会员手机号码
     */
    private String mobile;

    /**
     * 手机号国家代码
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * 性别：男性（1），女性（2）
     */
    private Byte sex;

    /**
     * 出生日期
     */
    private Date birthday;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取会员昵称
     *
     * @return nickname - 会员昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置会员昵称
     *
     * @param nickname 会员昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取会员手机号码
     *
     * @return mobile - 会员手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置会员手机号码
     *
     * @param mobile 会员手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取手机号国家代码
     *
     * @return country_code - 手机号国家代码
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置手机号国家代码
     *
     * @param countryCode 手机号国家代码
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 获取头像
     *
     * @return headimgurl - 头像
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置头像
     *
     * @param headimgurl 头像
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取性别：男性（1），女性（2）
     *
     * @return sex - 性别：男性（1），女性（2）
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别：男性（1），女性（2）
     *
     * @param sex 性别：男性（1），女性（2）
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}