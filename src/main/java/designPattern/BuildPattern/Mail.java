package designPattern.BuildPattern;

import lombok.Data;

import java.util.Date;

/**
 * 欢迎、欢送邮件系统
 * Created by Xu on 2017/3/24.
 */
public class Mail {
    public static void main(String[] args) {
        MailBuilder builder = new WelcomeBuilder();
        MailDirector director = new MailDirector(builder);
        director.construct("to@qq.com", "from@qq.com");
        builder.sendMessage();
    }
}

@Data
abstract class AutoMessage {
    private String to;
    private String from;
    private String subject;
    private String body;
    private Date sendDate;

    public void send() {
        System.out.println("收件人地址：" + to);
        System.out.println("发件人地址：" + from);
        System.out.println("标题：" + subject);
        System.out.println("内容：" + body);
        System.out.println("发送日期：" + sendDate);
    }
}

class WelcomeMessage extends AutoMessage {
    public WelcomeMessage() {
        System.out.println("欢迎发送信息");
    }
}

class GoodbyeMessage extends AutoMessage {
    public GoodbyeMessage() {
        System.out.println("欢迎发送信息");
    }
}

abstract class MailBuilder {
    protected AutoMessage msg;
    //标题零件的建造方法
    public abstract void buildSubject();
    //内容零件的建造方法
    public abstract void buildBody();
    //收件人零件的建造方法
    public void buildTo(String to){
        msg.setTo(to);
    }
    //发件人零件的建造方法
    public void buildFrom(String from){
        msg.setFrom(from);
    }
    //发送时间零件的建造方法
    public void buildSendDate(){
        msg.setSendDate(new Date());
    }

    public void sendMessage() {
        msg.send();
    }
}

class WelcomeBuilder extends MailBuilder {
    public WelcomeBuilder() {
        msg = new WelcomeMessage();
    }

    @Override
    public void buildSubject() {
        msg.setSubject("欢迎标题");
    }

    @Override
    public void buildBody() {
        msg.setBody("欢迎内容");
    }
}

class GoodbyeBuilder extends MailBuilder {

    public GoodbyeBuilder() {
        msg = new GoodbyeMessage();
    }

    @Override
    public void buildSubject() {
        msg.setSubject("欢送标题");
    }

    @Override
    public void buildBody() {
        msg.setBody("欢送内容");
    }
}

class MailDirector {
    MailBuilder builder;

    public MailDirector(MailBuilder builder) {
        this.builder = builder;
    }

    public void construct(String toAddress, String fromAddress) {
        builder.buildTo(toAddress);
        builder.buildFrom(fromAddress);
        builder.buildBody();
        builder.buildSubject();
        builder.buildSendDate();
    }
}