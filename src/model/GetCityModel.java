package model;

public class GetCityModel {

    /**
     * resultcode : 200
     * reason : 查询成功
     * result : {"Country":"中国","Province":"广西","City":"北海市","Isp":"电信"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * Country : 中国
         * Province : 广西
         * City : 北海市
         * Isp : 电信
         */

        private String Country;
        private String Province;
        private String City;
        private String Isp;

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getIsp() {
            return Isp;
        }

        public void setIsp(String Isp) {
            this.Isp = Isp;
        }
    }
}
