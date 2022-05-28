package top.sakura70s.sevx.beans;

import java.io.Serializable;

public class NovelBean implements Serializable {

    private Integer id;
    private Boolean seriesflag;
    private Integer seriesid;
    private String novel_name;
    private String novel_year;
    private String novel_status;
    private String logo;
    private String author;
    private Boolean localflag;
    private String localurl;
    private Boolean remoteflag;
    private String remoteurl;
    private String container;
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

    public String getNovel_name() {
        return novel_name;
    }

    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getNovel_year() {
        return novel_year;
    }

    public void setNovel_year(String novel_year) {
        this.novel_year = novel_year;
    }

    public String getNovel_status() {
        return novel_status;
    }

    public void setNovel_status(String novel_status) {
        this.novel_status = novel_status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
