package com.szzt.iot.admin.modules.websocket;

import com.szzt.iot.common.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("ws")
public class PushMsgController {//推送数据接口

    @RequestMapping("pushToWeb/{sid}")
    public Result pushToWeb(@PathVariable Long sid, String message) throws IOException {
        RobotWebSocketHandler.sendMessage(sid, message);
        return new Result();
    }


}
