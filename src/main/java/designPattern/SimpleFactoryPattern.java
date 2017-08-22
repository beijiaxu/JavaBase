package designPattern;

/**
 * 简单工厂模式
 * Created by Xu on 2017/3/23.
 */
public class SimpleFactoryPattern {
    public static void main(String[] args) {
        Login passwordLogin = LoginManager.factory("password");
        Login domainLogin = LoginManager.factory("domain");
    }


}

interface Login {
    boolean verify();
}

class PasswordLogin implements Login {
    @Override
    public boolean verify() {
        //密码验证
        return false;
    }
}

class DomainLogin implements Login {

    @Override
    public boolean verify() {
        //域验证
        return false;
    }
}

class LoginManager {
    static Login factory(String name) {
        switch (name) {
            case "password":
                System.out.println("password");
                return new PasswordLogin();
            case "domain":
                System.out.println("domain");
                return new DomainLogin();
        }
        throw new RuntimeException("未找到合适的登陆类型");
    }
}

