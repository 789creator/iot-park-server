package com.szzt.iot.script;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.io.File;

public class ScriptProcessTest {

    @Test
    public void scriptJsonPrase() {
        ClassPathResource classPathResource = new ClassPathResource("test");
        String path = classPathResource.getAbsolutePath() + File.separator + "script_process.json";
        JSONArray jsonArray = (JSONArray) JSONUtil.readJSON(new File(path), CharsetUtil.CHARSET_UTF_8);
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            System.out.println(jsonObject.get("script_name"));
        }
    }

}
