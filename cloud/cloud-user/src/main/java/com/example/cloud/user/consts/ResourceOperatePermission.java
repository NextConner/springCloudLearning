package com.example.cloud.user.consts;

/**
 * 默认的权限集
 *
 * @author jintaoZou
 * @date 2022/5/12-10:48
 */
public enum ResourceOperatePermission {

    C,
    R,
    D,
    U,
    NONE,
    ALL;

    public static ResourceOperatePermission[] CRUD() {
        return new ResourceOperatePermission[]{
                C,
                R,
                D,
                U
        };
    }

    public static ResourceOperatePermission[] CRU() {
        return new ResourceOperatePermission[]{
                C,
                R,
                U
        };
    }

    public static ResourceOperatePermission[] CRD() {
        return new ResourceOperatePermission[]{
                C,
                R,
                D
        };
    }

    public static ResourceOperatePermission[] RU() {
        return new ResourceOperatePermission[]{
                R,
                U
        };
    }

    public static ResourceOperatePermission[] RD() {
        return new ResourceOperatePermission[]{
                R,
                D
        };
    }

    public static ResourceOperatePermission[] CR() {
        return new ResourceOperatePermission[]{
                C,
                R
        };
    }
}
