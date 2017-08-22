package designPattern;

/**
 * 桥梁模式
 * Created by Xu on 2017/4/4.
 */
public class BridgePattern {
    public static void main(String[] args) {
        //创建具体的实现对象
        MessageImplementor impl = new MessageSMS();
        //创建普通消息对象
        AbstractMessage message = new  CommonMessage(impl);
        message.sendMessage("加班申请速批","李总");

        //将实现方式切换成邮件，再次发送
        impl = new MessageEmail();
        //创建加急消息对象
        message = new UrgencyMessage(impl);
        message.sendMessage("加班申请速批","李总");
    }
}

abstract class AbstractMessage {
    //持有一个实现部分的对象
    MessageImplementor impl;
    /**
     * 构造方法，传入实现部分的对象
     * @param impl  实现部分的对象
     */
    public AbstractMessage(MessageImplementor impl){
        this.impl = impl;
    }
    /**
     * 发送消息，委派给实现部分的方法
     * @param message    要发送消息的内容
     * @param toUser    消息的接受者
     */
    public void sendMessage(String message , String toUser){
        this.impl.send(message, toUser);
    }
}

class CommonMessage extends AbstractMessage {

    public CommonMessage(MessageImplementor impl) {
        super(impl);
    }
    @Override
    public void sendMessage(String message, String toUser) {
        // 对于普通消息，直接调用父类方法，发送消息即可
        super.sendMessage(message, toUser);
    }
}

class UrgencyMessage extends AbstractMessage {

    public UrgencyMessage(MessageImplementor impl) {
        super(impl);
    }
    @Override
    public void sendMessage(String message, String toUser) {
        message = "加急：" + message;
        super.sendMessage(message, toUser);
    }
    /**
     * 扩展自己的新功能，监控某消息的处理状态
     * @param messageId    被监控的消息编号
     * @return    监控到的消息的处理状态
     */
    public Object watch(String messageId) {
        // 根据消息id获取消息的状态，组织成监控的数据对象，然后返回
        return null;
    }
}

interface MessageImplementor {
    /**
     * 发送消息
     * @param message 要发送消息的内容
     * @param toUser  消息的接受者
     */
    public void send(String message , String toUser);
}

class MessageSMS implements MessageImplementor {

    @Override
    public void send(String message, String toUser) {

        System.out.println("使用系统内短消息的方法，发送消息'"+message+"'给"+toUser);
    }

}

class MessageEmail implements MessageImplementor {

    @Override
    public void send(String message, String toUser) {
        System.out.println("使用邮件短消息的方法，发送消息'"+message+"'给"+toUser);
    }

}