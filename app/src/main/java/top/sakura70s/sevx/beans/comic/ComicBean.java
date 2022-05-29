package top.sakura70s.sevx.beans.comic;

import java.io.Serializable;

public class ComicBean implements Serializable {

    private Integer id;
    private Boolean seriesflag;
    private Integer seriesid;
    private String comic_name;
    private String comic_year;
    private String comic_status;
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

    public String getComic_name() {
        return comic_name;
    }

    public void setComic_name(String comic_name) {
        this.comic_name = comic_name;
    }

    public String getComic_year() {
        return comic_year;
    }

    public void setComic_year(String comic_year) {
        this.comic_year = comic_year;
    }

    public String getComic_status() {
        return comic_status;
    }

    public void setComic_status(String comic_status) {
        this.comic_status = comic_status;
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
