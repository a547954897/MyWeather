package model;

import java.util.List;


public class SeachCityModel {

    /**
     * 城市信息搜索 model
     * code : 200
     * location : [{"name":"北海","id":"101301301","lat":"21.47334","lon":"109.11925","adm2":"北海","adm1":"广西壮族自治区","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"http://hfx.link/3mp1"},{"name":"北海","id":"A4243","lat":"5.39799","lon":"100.37699","adm2":"北海","adm1":"槟城","country":"马来西亚","tz":"Asia/Kuala_Lumpur","utcOffset":"+08:00","isDst":"0","type":"city","rank":"45","fxLink":"http://hfx.link/1ybf1"},{"name":"海城","id":"101301304","lat":"21.46844","lon":"109.10752","adm2":"北海","adm1":"广西壮族自治区","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1u2l1"},{"name":"涠洲岛","id":"101301303","lat":"21.04219","lon":"109.11660","adm2":"北海","adm1":"广西壮族自治区","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/21tx1"},{"name":"合浦","id":"101301302","lat":"21.66355","lon":"109.20069","adm2":"北海","adm1":"广西壮族自治区","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/3mq1"},{"name":"银海","id":"101301305","lat":"21.44490","lon":"109.11870","adm2":"北海","adm1":"广西壮族自治区","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"45","fxLink":"http://hfx.link/1u2m1"},{"name":"铁山港","id":"101301306","lat":"21.59280","lon":"109.45057","adm2":"北海","adm1":"广西壮族自治区","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"45","fxLink":"http://hfx.link/1u2n1"},{"name":"带广市","id":"6198A","lat":"42.92361","lon":"143.19667","adm2":"北海道","adm1":"北海道","country":"日本","tz":"Asia/Tokyo","utcOffset":"+09:00","isDst":"0","type":"city","rank":"45","fxLink":"http://hfx.link/1y7h1"},{"name":"旭川市","id":"6E319","lat":"43.76699","lon":"142.34899","adm2":"旭川市","adm1":"北海道","country":"日本","tz":"Asia/Tokyo","utcOffset":"+09:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1y561"},{"name":"函馆市","id":"EC85E","lat":"41.77333","lon":"140.72610","adm2":"Oshima Subprefecture","adm1":"北海道","country":"日本","tz":"Asia/Tokyo","utcOffset":"+09:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1y5p1"}]
     * refer : {"sources":["heweather.com"],"license":["commercial license"]}
     */

    private String code;
    private ReferBean refer;
    private List<LocationBean> location;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<LocationBean> getLocation() {
        return location;
    }

    public void setLocation(List<LocationBean> location) {
        this.location = location;
    }

    public static class ReferBean {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class LocationBean {
        /**
         * name : 北海
         * id : 101301301
         * lat : 21.47334
         * lon : 109.11925
         * adm2 : 北海
         * adm1 : 广西壮族自治区
         * country : 中国
         * tz : Asia/Shanghai
         * utcOffset : +08:00
         * isDst : 0
         * type : city
         * rank : 23
         * fxLink : http://hfx.link/3mp1
         */

        private String name;
        private String id;
        private String lat;
        private String lon;
        private String adm2;
        private String adm1;
        private String country;
        private String tz;
        private String utcOffset;
        private String isDst;
        private String type;
        private String rank;
        private String fxLink;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getAdm2() {
            return adm2;
        }

        public void setAdm2(String adm2) {
            this.adm2 = adm2;
        }

        public String getAdm1() {
            return adm1;
        }

        public void setAdm1(String adm1) {
            this.adm1 = adm1;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getIsDst() {
            return isDst;
        }

        public void setIsDst(String isDst) {
            this.isDst = isDst;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFxLink() {
            return fxLink;
        }

        public void setFxLink(String fxLink) {
            this.fxLink = fxLink;
        }
    }
}
