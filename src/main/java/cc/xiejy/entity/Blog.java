package cc.xiejy.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xie on 2017/4/11 0011.
 */
public class Blog {

    private Integer id;
    private String title;
    private String summary;
    private Date releaseDate;
    private String releaseDateStr;
    private Integer click;
    private Integer reply;
    private String content;
    private String keyword;

    private String plainContent;
    /**
     * 在表中对应的列为typeId，查询时调用BlogTypeDao的getBlogTypeById方法
     */
    private BlogType blogType;

    //在查询出博客后，从它的内容中取出前三个img标签，当作缩略图放入这个字段
    private List<String> imageList = new LinkedList<>();

    private int blogCountForSameDate;

    public String getPlainContent() {
        return plainContent;
    }

    public void setPlainContent(String plainContent) {
        this.plainContent = plainContent;
    }

    public void setBlogCountForSameDate(int blogCountForSameDate) {
        this.blogCountForSameDate = blogCountForSameDate;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
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

    public Integer getBlogCountForSameDate() {
        return blogCountForSameDate;
    }

    public void setBlogCountForSameDate(Integer blogCountForSameDate) {
        this.blogCountForSameDate = blogCountForSameDate;
    }
}
