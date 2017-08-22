package designPattern;

/**
 * 工厂模式
 * Created by Xu on 2017/3/23.
 */
public class FactoryPattern {
    public static void main(String[] args) {
        String data = "";
        ExportFactory exportFactory = new ExportHtmlFactory();
        ExportFile ef = exportFactory.factory("financial");
        ef.export(data);
        ExportFactory exportFactory2 = new ExportPdfFactory();
        ExportFile ef2 = exportFactory2.factory("standard");
        ef2.export(data);
    }
}

/**
 * 工厂方法
 */
interface ExportFactory {
    ExportFile factory(String type);
}
class ExportHtmlFactory implements ExportFactory {

    @Override
    public ExportFile factory(String type) {
        switch (type) {
            case "standard":
                return new ExportStandardHtmlFile();
            case "financial":
                return new ExportFinancialHtmlFile();
        }
        throw new RuntimeException("没有找到对象");
    }
}
class ExportPdfFactory implements ExportFactory {

    @Override
    public ExportFile factory(String type) {
        switch (type) {
            case "standard":
                return new ExportStandardPdfFile();
            case "financial":
                return new ExportFinancialPdfFile();
        }
        throw new RuntimeException("没有找到对象");
    }
}

/**
 * 导出文件方法
 */
interface ExportFile {
    boolean export(String data);
}
class ExportStandardHtmlFile implements ExportFile {

    @Override
    public boolean export(String data) {
        System.out.println("导出标准HTML文件");
        return true;
    }
}

class ExportFinancialHtmlFile implements ExportFile {

    @Override
    public boolean export(String data) {
        System.out.println("导出财务版HTML文件");
        return true;
    }
}

class ExportStandardPdfFile implements ExportFile {

    @Override
    public boolean export(String data) {
        System.out.println("导出标准PDF文件");
        return true;
    }
}

class ExportFinancialPdfFile implements ExportFile {

    @Override
    public boolean export(String data) {
        System.out.println("导出财务版PDF文件");
        return true;
    }
}



