/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 * <p>
 * https://www.szzt.com.cn
 * <p>
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.message.email;

import cn.hutool.core.map.MapUtil;
import com.szzt.iot.admin.modules.message.entity.SysMailTemplateEntity;
import com.szzt.iot.admin.modules.message.service.SysMailLogService;
import com.szzt.iot.admin.modules.message.service.SysMailTemplateService;
import com.szzt.iot.admin.modules.sys.service.SysParamsService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.exception.ErrorCode;
import com.szzt.iot.common.exception.RobotException;
import com.szzt.iot.common.utils.FtpUtils;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import java.io.*;
import java.security.Security;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件工具类
 *
 * @author
 */
@Component
public class EmailUtils {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysParamsService sysParamsService;
    @Autowired
    private SysMailTemplateService sysMailTemplateService;
    @Autowired
    private SysMailLogService sysMailLogService;

    private final static String KEY = Constant.MAIL_CONFIG_KEY;

    private JavaMailSenderImpl createMailSender(EmailConfig config) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(config.getSmtp());
        sender.setPort(config.getPort());
        sender.setUsername(config.getUsername());
        sender.setPassword(config.getPassword());
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "10000");
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param templateId 模板ID
     * @param to         收件人
     * @param cc         抄送
     * @param params     模板参数
     * @return true：成功   false：失败
     */
    public boolean sendMail(Long templateId, String[] to, String[] cc, Map<String, Object> params) throws Exception {
        SysMailTemplateEntity template = sysMailTemplateService.selectById(templateId);
        if (template == null) {
            throw new RobotException(ErrorCode.MAIL_TEMPLATE_NOT_EXISTS);
        }

        EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);
        JavaMailSenderImpl mailSender = createMailSender(config);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //设置utf-8编码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(config.getUsername());

        //收件人
        messageHelper.setTo(to);
        //抄送
        if (cc != null && cc.length > 0) {
            messageHelper.setCc(cc);
        }
        //主题
        messageHelper.setSubject(template.getSubject());

        //邮件正文
        String content = getFreemarkerContent(template.getContent(), params);
        messageHelper.setText(content, true);

        int status = Constant.SUCCESS;
        //发送邮件
        try {
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            status = Constant.FAIL;
            logger.error("send error", e);
        }

        sysMailLogService.save(templateId, config.getUsername(), to, cc, template.getSubject(), content, status);

        return status == Constant.SUCCESS ? true : false;
    }

    /**
     * 获取Freemarker渲染后的内容
     *
     * @param content 模板内容
     * @param params  参数
     */
    public String getFreemarkerContent(String content, Map<String, Object> params) throws Exception {
        if (MapUtil.isEmpty(params)) {
            return content;
        }

        //模板
        StringReader reader = new StringReader(content);
        Template template = new Template("mail", reader, null, "utf-8");

        //渲染模板
        StringWriter sw = new StringWriter();
        template.process(params, sw);

        content = sw.toString();
        IOUtils.closeQuietly(sw);

        return content;
    }

    /**
     * 发送邮件
     *
     * @param to      收件人
     * @param cc      抄送
     * @param subject 主题
     * @param content 邮件正文
     * @return true：成功   false：失败
     */
    public boolean sendMail(String[] to, String[] cc, String subject, String content) throws Exception {
        EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);
        JavaMailSenderImpl mailSender = createMailSender(config);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //设置utf-8编码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(config.getUsername());

        //收件人
        messageHelper.setTo(to);
        //抄送
        if (cc != null && cc.length > 0) {
            messageHelper.setCc(cc);
        }
        //主题
        messageHelper.setSubject(subject);
        //邮件正文
        messageHelper.setText(content, true);

        int status = Constant.SUCCESS;
        //发送邮件
        try {
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            status = Constant.FAIL;
            logger.error("send error", e);
        }

        sysMailLogService.save(null, config.getUsername(), to, cc, subject, content, status);

        return status == Constant.SUCCESS ? true : false;
    }

    public Store getStore() throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//ssl加密,jdk1.8无法使用
        EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);
        Properties props = new Properties();
        //TODO 这里现在只写死，用imap
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.transport.protocol", config.getReceiveProtocol()); // 使用的协议
        props.setProperty("mail.imap.port", config.getReceivePort());
        props.setProperty("mail.imap.socketFactory.port", config.getReceivePort());
        // 获取连接
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        // 获取Store对象
        Store store = session.getStore(config.getReceiveProtocol());
        store.connect(config.getReceiveServer(), config.getUsername(), config.getPassword()); // 登陆认证
        return store;
    }

    /**
     * 接收未读邮件，并且标记为已读
     */
    public void receiveEmail() throws Exception {
        Store store = this.getStore();
        // 通过协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
        // 获得用户的邮件帐户
        Folder folder = store.getFolder("INBOX");
        // 设置对邮件帐户的访问权限
        folder.open(Folder.READ_WRITE);

        // 得到未读数量
        int n = folder.getUnreadMessageCount();
        System.out.println("unread" + n);
        // false代表未读，true代表已读
        FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message messages[] = folder.search(ft);
        for (Message message : messages) {
            // 获得邮件主题
            String subject = message.getSubject();
            // 获得发送者地址
            Address from = (Address) message.getFrom()[0];

            String s = decodeText(from.toString());
            String mailSender = s.split("<")[1].split(">")[0];
            Date receivedDate = message.getReceivedDate();
            System.out.println("邮件的主题为: " + subject);
            System.out.println("发件人地址为: " + s);
            System.out.println("日期:" + message.getSentDate());
/*            Enumeration headers = message.getAllHeaders();
              System.out.println("----------------------allHeaders-----------------------------");
              while (headers.hasMoreElements()) {
                     Header header = (Header)headers.nextElement();
                     System.out.println(header.getName()+" ======= "+header.getValue());
                     System.out.println("----------------------------");
                 }*/
            // 上传附件并保存任务信息
            saveAttachment((Part) message, mailSender, receivedDate);
            parseMultipart((Multipart) message.getContent());

            //imap读取后邮件状态会变为已读,设为未读
            message.setFlag(Flags.Flag.SEEN, true);
        }

        // 关闭邮件夹对象
        folder.close(false);
        // 关闭连接对象
        store.close();
    }

    protected String decodeText(String text) throws UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        if (text.startsWith("=?GB") || text.startsWith("=?gb")) {
            text = MimeUtility.decodeText(text);
        } else {
            text = new String(text.getBytes("ISO8859_1"));
        }
        return text;
    }

    /**
     * 对复杂邮件的解析
     *
     * @param multipart
     * @throws MessagingException
     * @throws IOException
     */
    public void parseMultipart(Multipart multipart) throws MessagingException, IOException {
        int count = multipart.getCount();
        System.out.println("couont =  " + count);
        for (int idx = 0; idx < count; idx++) {
            BodyPart bodyPart = multipart.getBodyPart(idx);
            System.out.println(bodyPart.getContentType());
            if (bodyPart.isMimeType("text/plain")) {
                System.out.println("plain................." + bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                System.out.println("html..................." + bodyPart.getContent());
            } else if (bodyPart.isMimeType("multipart/*")) {
                Multipart mpart = (Multipart) bodyPart.getContent();
                parseMultipart(mpart);

            } else if (bodyPart.isMimeType("application/octet-stream")) {
                String disposition = bodyPart.getDisposition();
                System.out.println(disposition);
                if (disposition.equalsIgnoreCase(BodyPart.ATTACHMENT)) {
                    String fileName = decodeText(bodyPart.getFileName());
                    InputStream is = bodyPart.getInputStream();
                    // todo 上传到fpt文件夹
                    copy(is, new FileOutputStream("D:\\" + fileName));
                }
            }
        }
    }

    /**
     * 【上传附件】 　
     *
     * @throws Exception
     */
    public void saveAttachment(Part part, String mailSender, Date receivedDate) throws Exception {
        FtpUtils ftpUtils = new FtpUtils();
        String fileName = "";
        String filePath = "/download";
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int j = 0; j < mp.getCount(); j++) {
                BodyPart mpart = mp.getBodyPart(j);
                String disposition = mpart.getDescription();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
                    fileName = mpart.getFileName();
                    if (fileName.toLowerCase().indexOf("GBK") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    ftpUtils.uploadFile(filePath, fileName, mpart.getInputStream());
                    // 保存任务信息
                } else if (mpart.isMimeType("multipart/*")) {
                    fileName = mpart.getFileName();
                } else {
                    fileName = mpart.getFileName();
                    if ((fileName != null)) {
                        fileName = MimeUtility.decodeText(fileName);
                        ftpUtils.uploadFile(filePath, fileName, mpart.getInputStream());
                        // 保存任务信息
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachment((Part) part.getContent(), mailSender, receivedDate);
        }
    }

    /**
     * 文件拷贝，在用户进行附件下载的时候，可以把附件的InputStream传给用户进行下载
     *
     * @param is
     * @param os
     * @throws IOException
     */
    public void copy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = is.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        if (os != null) {
            os.close();
        }
        if (is != null) {
            is.close();
        }
    }

}
