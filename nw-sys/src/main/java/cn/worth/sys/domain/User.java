package cn.worth.sys.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chenxiaoqing
 * @since 2019-08-06
 */
@Getter
@Setter
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别 1-男 2-女
     */
    private Integer sex;
    /**
     * 0-普通 1-管理员 2-微信用户
     */
    private Integer type;
    /**
     * 随机盐
     */
    private String salt;
    /**
     * 姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;
    /**
     * 状态 0-正常 1-锁住 2- 过期
     */
    private Integer status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_update")
    private Date gmtUpdate;
    /**
     * 0-正常，1-删除
     */
    @TableField("del_flag")
    private Integer delFlag;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

