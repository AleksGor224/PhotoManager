package com.exampleapp.my_example_app.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class PhotoEntity {

    @Column(name = "albumId")
    private int albumId;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "thumbnailUrl")
    private String thumbnailUrl;
    @Column(name = "downloadDateTime")
    private String downloadDateTime;
    @Column(name = "localPath")
    private String localPath;
    @Column(name = "fileSize")
    private int fileSize;

    public PhotoEntity() {
    }

    public PhotoEntity(int albumId, int id, String title, String url, String thumbnailUrl, String downloadDateTime, String localPath, int fileSize) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.downloadDateTime = downloadDateTime;
        this.localPath = localPath;
        this.fileSize = fileSize;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDownloadDateTime() {
        return downloadDateTime;
    }

    public void setDownloadDateTime(String downloadDateTime) {
        this.downloadDateTime = downloadDateTime;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoEntity entity = (PhotoEntity) o;
        return albumId == entity.albumId &&
                id == entity.id &&
                fileSize == entity.fileSize &&
                Objects.equals(title, entity.title) &&
                Objects.equals(url, entity.url) &&
                Objects.equals(thumbnailUrl, entity.thumbnailUrl) &&
                Objects.equals(downloadDateTime, entity.downloadDateTime) &&
                Objects.equals(localPath, entity.localPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, id, title, url, thumbnailUrl, downloadDateTime, localPath, fileSize);
    }

    //Builder
    public static Builder builder() {
        return new PhotoEntity().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder albumId(int albumId) {
            PhotoEntity.this.albumId = albumId;
            return this;
        }

        public Builder id(int id){
            PhotoEntity.this.id = id;
            return this;
        }

        public Builder title(String title){
            PhotoEntity.this.title = title;
            return this;
        }

        public Builder url(String path){
            PhotoEntity.this.url = path;
            return this;
        }

        public Builder thumbnailUrl(String path){
            PhotoEntity.this.thumbnailUrl = path;
            return this;
        }

        public PhotoEntity build() {
            return PhotoEntity.this;
        }

    }
}