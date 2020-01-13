package com.simple.server.netty;

import com.simple.server.action.ActionManager;
import com.simple.server.login.action.LoginAction;
import com.simple.server.proto.Request;
import com.simple.server.session.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SocketServerHandler extends SimpleChannelInboundHandler<Request> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
          }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SessionManager sessionManager = SessionManager.getSessionManager();
        sessionManager.addSession(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        int cmd = request.getCmd();
        if (cmd == 10001){
            LoginAction loginAction = new LoginAction();
            loginAction.action(cmd, request, ctx.channel());
        }
        ActionManager actionManager = new ActionManager();
        actionManager.dispatch(request);
    }

}
