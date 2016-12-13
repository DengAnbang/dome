package com.home.dab.dome.demo.net.download;


/**
 * Created by DAB on 2016/12/9 10:12.
 */

public class DownloadInfo {

    private Long id;

    private String savePath;
    private String url;
    private long contentLength;
    private long bytesReaded;

    public String getUrl() {
        return url;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getBytesReaded() {
        return bytesReaded;
    }

    public void setBytesReaded(long bytesReaded) {
        this.bytesReaded = bytesReaded;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DownloadInfo(String savePath, String url, long contentLength) {
        this.savePath = savePath;
        this.url = url;
        this.contentLength = contentLength;
    }


    public static class Builder {
        private String savePath;
        private String url;
        private long contentLength;

        public Builder setSavePath(String savePath) {
            this.savePath = savePath;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setContentLength(long contentLength) {
            this.contentLength = contentLength;
            return this;
        }

        public DownloadInfo Build() {
            return new DownloadInfo(savePath, url, contentLength);
        }
    }
}
