package com.yucheng.estm.task;

import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.entity.WechatSend;
import com.yucheng.estm.entity.WechatSendHis;
import com.yucheng.estm.service.WechatService;
import com.yucheng.estm.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Component
@EnableScheduling
public class SendWechatTask {
    private static Logger log = Logger.getLogger(SendWechatTask.class);

    @Autowired
    private WechatService wechatService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendMsg() {
        List<WechatSend> list = wechatService.getAllRecond();
        String errorMessage = "";

        for (WechatSend wechatSend : list) {
            int state = MessageContant.SEND_STATE_OK;
            WechatSendHis wechatSendHis = new WechatSendHis();
            try {
                wechatService.doSendWechat(wechatSend);

            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));

                state = MessageContant.SEND_STATE_ERR;
                errorMessage = sw.getBuffer().toString();
            } finally {
                //推送完成后,不管成功或者失败。删除原表数据，插入历史表数据
                wechatSendHis.setAuditId(wechatSend.getAuditId());
                wechatSendHis.setOutUser(wechatSend.getOutUser());
                wechatSendHis.setCotent(wechatSend.getCotent());
                wechatSendHis.setCreateDate(wechatSend.getCreateDate());
                wechatSendHis.setSendDate(wechatSend.getSendDate());
                wechatSendHis.setTemplateId(wechatSend.getTemplateId());
                wechatSendHis.setFinishDate(DateUtil.getCurrentDate());
                wechatSendHis.setState(state);
                wechatSendHis.setSendLog(errorMessage);

                wechatService.move2His(wechatSend, wechatSendHis);
            }
        }
    }
}