package InnerClass;

/**
 * 作用域内的内部类
 * Created by Xu on 2017/3/24.
 */
public class OuterClass4 {
    private void internalTracking(boolean b) {
        if(b) {
            class TrackingSlip {
                private String id;
                TrackingSlip(String s) {
                    id = s;
                }
                String getSlip() {
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("chenssy");
            String s = ts.getSlip();
        }
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        OuterClass4 outer = new OuterClass4();
        outer.track();
    }
}
