package edu.nuc.light_system.entity.vo;

/**
 * @ClassName UserInfoVO
 * @Description //用户视图类
 * @Author 19265
 * @Date 2026/4/9 16:07
 * @Version 1.0.0
 */
public class UserVO {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户权限
     */
    private Integer right;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * token
     */
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
