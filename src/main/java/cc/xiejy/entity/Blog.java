package cc.xiejy.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xie on 2017/4/11 0011.
 */
public class Blog {

    private int id;
    private String title;
    private String summary;
    private Date releaseDate;
    private String releaseDateStr;
    private int click;
    private int reply;
    private String content;
    private String keyword;

    private BlogType blogType;

    private List<String> imageList = new LinkedList<>();
    private int blogCountForSameDate;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getBlogCountForSameDate() {
        return blogCountForSameDate;
    }

    public void setBlogCountForSameDate(int blogCountForSameDate) {
        this.blogCountForSameDate = blogCountForSameDate;
    }
}
