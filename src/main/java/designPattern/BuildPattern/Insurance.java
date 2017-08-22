package designPattern.BuildPattern;

/**
 * Created by Xu on 2017/3/24.
 */
public class Insurance {
    public static void main(String[] args) {

        InsuranceContract.InsuranceBuilder builder = new InsuranceContract.InsuranceBuilder("9257", 123L, 456L);
        InsuranceContract contract = builder.setCompanyName("加贝").setOtherData("test").build();
        contract.someOperation();
    }
}

class InsuranceContract {
    private String contractId;
    private String personName;
    private String companyName;
    private long beginDate;
    private long endDate;
    private String otherData;

    private InsuranceContract(InsuranceBuilder builder) {
        this.contractId = builder.contractId;
        this.personName = builder.personName;
        this.companyName = builder.companyName;
        this.beginDate = builder.beginDate;
        this.endDate = builder.endDate;
        this.otherData = builder.otherData;
    }

    public void someOperation() {
        System.out.println("当前正在操作的保险合同编号为【" + contractId + "】");
    }

    public static class InsuranceBuilder {
        private String contractId;
        private String personName;
        private String companyName;
        private long beginDate;
        private long endDate;
        private String otherData;

        public InsuranceBuilder(String contractId, long beginDate, long endDate) {
            this.contractId = contractId;
            this.beginDate = beginDate;
            this.endDate = endDate;
        }

        public InsuranceBuilder setPersonName(String personName) {
            this.personName = personName;
            return this;
        }

        public InsuranceBuilder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public InsuranceBuilder setOtherData(String otherData) {
            this.otherData = otherData;
            return this;
        }

        public InsuranceContract build() {
            if(contractId == null || contractId.trim().length() == 0)
                throw new IllegalArgumentException("合同编号不能为空");

            boolean signPerson = (personName != null && personName.trim().length() > 0);
            boolean signCompany = (companyName != null && companyName.trim().length() > 0);
            if(signCompany && signPerson)
                throw new IllegalArgumentException("一份保险合同不能同时与个人和公司签订");
            if(signPerson  == false && signCompany == false)
                throw new IllegalArgumentException("一份保险合同不能没有签订对象");
            if(beginDate <= 0 )
                throw new IllegalArgumentException("一份保险合同必须有开始生效的日期");
            if(endDate <=0)
                throw new IllegalArgumentException("一份保险合同必须有失效的日期");
            if(endDate < beginDate)
                throw new IllegalArgumentException("一份保险合同的失效日期必须大于生效日期");

            return new InsuranceContract(this);
        }
    }

}


