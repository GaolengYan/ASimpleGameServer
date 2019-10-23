package com.simple.server.action;

import com.google.protobuf.MessageLite;

/**
 * @Author：yanweifan
 * @E-mail；a349162727@qq.com
 * @CreateDate：2019/10/20
 * @Description:
 */
public interface Action {
    void action(int cmd, MessageLite messageLite);
}
