package designPattern.ProducerConsumerPattern;

/**
 * Created by Xu on 2017/7/17.
 */
public final class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
