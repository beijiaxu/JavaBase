package designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式
 * Created by Xu on 2017/4/9.
 */
public class CommandPattern {
    public static void main(String[]args){
//        //创建接收者对象
//        AudioPlayer audioPlayer = new AudioPlayer();
//        //创建命令对象
//        Command playCommand = new PlayCommand(audioPlayer);
//        Command rewindCommand = new RewindCommand(audioPlayer);
//        Command stopCommand = new StopCommand(audioPlayer);
//        //创建请求者对象
//        Keypad keypad = new Keypad();
//        keypad.setPlayCommand(playCommand);
//        keypad.setRewindCommand(rewindCommand);
//        keypad.setStopCommand(stopCommand);
//        //测试
//        keypad.play();
//        keypad.rewind();
//        keypad.stop();
//        keypad.play();
//        keypad.stop();

        //创建接收者对象
        AudioPlayer audioPlayer = new AudioPlayer();
        //创建命令对象
        Command playCommand = new PlayCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);

        MacroCommand marco = new MacroAudioCommand();

        marco.add(playCommand);
        marco.add(rewindCommand);
        marco.add(stopCommand);
        marco.execute();
    }
}

class AudioPlayer {

    public void play(){
        System.out.println("播放...");
    }

    public void rewind(){
        System.out.println("倒带...");
    }

    public void stop(){
        System.out.println("停止...");
    }
}

interface Command {
    /**
     * 执行方法
     */
    void execute();
}

class PlayCommand implements Command {

    private AudioPlayer myAudio;

    public PlayCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    /**
     * 执行方法
     */
    @Override
    public void execute() {
        myAudio.play();
    }

}

class RewindCommand implements Command {

    private AudioPlayer myAudio;

    public RewindCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.rewind();
    }

}

class StopCommand implements Command {
    private AudioPlayer myAudio;

    public StopCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.stop();
    }

}

class Keypad {
    private Command playCommand;
    private Command rewindCommand;
    private Command stopCommand;

    public void setPlayCommand(Command playCommand) {
        this.playCommand = playCommand;
    }
    public void setRewindCommand(Command rewindCommand) {
        this.rewindCommand = rewindCommand;
    }
    public void setStopCommand(Command stopCommand) {
        this.stopCommand = stopCommand;
    }
    /**
     * 执行播放方法
     */
    public void play(){
        playCommand.execute();
    }
    /**
     * 执行倒带方法
     */
    public void rewind(){
        rewindCommand.execute();
    }
    /**
     * 执行播放方法
     */
    public void stop(){
        stopCommand.execute();
    }
}

interface MacroCommand extends Command {
    /**
     * 宏命令聚集的管理方法
     * 可以添加一个成员命令
     */
    public void add(Command cmd);
    /**
     * 宏命令聚集的管理方法
     * 可以删除一个成员命令
     */
    public void remove(Command cmd);
}

class MacroAudioCommand implements MacroCommand {

    private List<Command> commandList = new ArrayList<>();
    /**
     * 宏命令聚集管理方法
     */
    @Override
    public void add(Command cmd) {
        commandList.add(cmd);
    }
    /**
     * 宏命令聚集管理方法
     */
    @Override
    public void remove(Command cmd) {
        commandList.remove(cmd);
    }
    /**
     * 执行方法
     */
    @Override
    public void execute() {
        for(Command cmd : commandList){
            cmd.execute();
        }
    }

}