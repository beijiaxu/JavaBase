package designPattern;

/**
 * 抽象工厂模式
 * Created by Xu on 2017/3/23.
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        ComputerEngineer engineer = new ComputerEngineer();
        AbstractFactory af = new IntelFactory();
        AbstractFactory af2 = new AmdFactory();
        engineer.makeComputer(af);
        engineer.makeComputer(af2);
    }
}

/**
 * cpu
 */
interface Cpu {
    void calculate();
}
class IntelCpu implements Cpu {

    private int pins;
    public IntelCpu(int pins) {
        this.pins = pins;
    }
    @Override
    public void calculate() {
        System.out.println("Intel CPU的针脚数：" + pins);
    }
}
class AmdCpu implements Cpu {

    private int pins;
    public AmdCpu(int pins) {
        this.pins = pins;
    }
    @Override
    public void calculate() {
        System.out.println("Amd CPU的针脚数：" + pins);
    }
}


/**
 * 主板
 */
interface Motherboard {
    void installCPU();
}
class IntelMotherboard implements Motherboard {

    private int cpuHoles;

    public IntelMotherboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("Intel主板的CPU插槽孔数是：" + cpuHoles);
    }
}
class AmdMotherboard implements Motherboard {

    private int cpuHoles;

    public AmdMotherboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("AMD主板的CPU插槽孔数是：" + cpuHoles);
    }
}

/**
 * 抽象工厂
 */
interface AbstractFactory {
    Cpu createCpu();
    Motherboard createMotherboard();
}
class IntelFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        return new IntelCpu(1151);
    }

    @Override
    public Motherboard createMotherboard() {
        return new IntelMotherboard(1151);
    }
}
class AmdFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        return new AmdCpu(1800);
    }

    @Override
    public Motherboard createMotherboard() {
        return new AmdMotherboard(1800);
    }
}

/**
 * 装机工程师
 */
class ComputerEngineer {
    private Cpu cpu = null;
    private Motherboard motherboard = null;
    public void makeComputer(AbstractFactory af) {
        prepareHardwares(af);
    }
    private void prepareHardwares(AbstractFactory af) {
        cpu = af.createCpu();
        motherboard = af.createMotherboard();
        cpu.calculate();
        motherboard.installCPU();
    }
}
