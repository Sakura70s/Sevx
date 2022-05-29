package top.sakura70s.sevx.beans.tv;

import java.io.Serializable;

public class VideoTvBean implements Serializable {

    private Integer id;
    private Boolean seriesflag;
    private Integer seriesid;
    private String tv_name;
    private String tv_year;
    private String director;
    private String screenwriter;
    private String make;
    private String logo;
    private Integer amount;
    private Boolean localflag;
    private String localurl;
    private Boolean remoteflag;
    private String remoteurl;
    private String container;
    private String codev;
    private String codea;
    private String subtype;
    private String subteam;
    private String lastwatch;
    private String updatetime;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSeriesflag() {
        return seriesflag;
    }

    public void setSeriesflag(Boolean seriesflag) {
        this.seriesflag = seriesflag;
    }

    public Integer getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_year() {
        return tv_year;
    }

    public void setTv_year(String tv_year) {
        this.tv_year = tv_year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getLocalflag() {
        return localflag;
    }

    public void setLocalflag(Boolean localflag) {
        this.localflag = localflag;
    }

    public String getLocalurl() {
        return localurl;
    }

    public void setLocalurl(String localurl) {
        this.localurl = localurl;
    }

    public Boolean getRemoteflag() {
        return remoteflag;
    }

    public void setRemoteflag(Boolean remoteflag) {
        this.remoteflag = remoteflag;
    }

    public String getRemoteurl() {
        return remoteurl;
    }

    public void setRemoteurl(String remoteurl) {
        this.remoteurl = remoteurl;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getCodev() {
        return codev;
    }

    public void setCodev(String codev) {
        this.codev = codev;
    }

    public String getCodea() {
        return codea;
    }

    public void setCodea(String codea) {
        this.codea = codea;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getSubteam() {
        return subteam;
    }

    public void setSubteam(String subteam) {
        this.subteam = subteam;
    }

    public String getLastwatch() {
        return lastwatch;
    }

    public void setLastwatch(String lastwatch) {
        this.lastwatch = lastwatch;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
