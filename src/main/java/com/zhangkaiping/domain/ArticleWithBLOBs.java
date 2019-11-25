package com.zhangkaiping.domain;

import java.io.Serializable;
import java.util.Date;

public class ArticleWithBLOBs extends Article implements Serializable{
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private String content;

    private String summary;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
    //	构造方法
    public ArticleWithBLOBs() {}
    
    
    
    
	public ArticleWithBLOBs(Integer id, String title, String picture, Integer channelId, Integer categoryId,
			Integer userId, Integer hits, Integer hot, Integer status, Integer deleted, Date created, Date updated,
			Integer contentType, String keywords, String original, String content, String summary) {
		super(id, title, picture, channelId, categoryId, userId, hits, hot, status, deleted, created, updated,
				contentType, keywords, original);
		this.content = content;
		this.summary = summary;
	}

	public ArticleWithBLOBs(Integer id, String title, String picture, Integer channelId, Integer categoryId,
			Integer userId, Integer hits, Integer hot, Integer status, Integer deleted, Date created, Date updated,
			Integer contentType, String keywords, String original) {
		super(id, title, picture, channelId, categoryId, userId, hits, hot, status, deleted, created, updated,
				contentType, keywords, original);
	}
    
    
    
}