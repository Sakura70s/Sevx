package top.sakura70s.sevx.beans.music;

import java.io.Serializable;

public class MusicBean implements Serializable {

    private Integer id;
    private String music_name;
    private String music_year;
    private String logo;
    private String artist;
    private String album;
    private String lyrics;
    private String written;
    private Boolean localflag;
    private String localurl;
    private Boolean remoteflag;
    private String remoteurl;
    private String container;
    private String lyrictype;
    private String updatetime;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getMusic_year() {
        return music_year;
    }

    public void setMusic_year(String music_year) {
        this.music_year = music_year;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getWritten() {
        return written;
    }

    public void setWritten(String written) {
        this.written = written;
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

    public String getLyrictype() {
        return lyrictype;
    }

    public void setLyrictype(String lyrictype) {
        this.lyrictype = lyrictype;
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
