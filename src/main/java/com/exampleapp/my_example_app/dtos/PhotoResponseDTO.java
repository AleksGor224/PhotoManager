package com.exampleapp.my_example_app.dtos;

import com.exampleapp.my_example_app.entities.PhotoEntity;

import java.util.Objects;

public class PhotoResponseDTO {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;
    private String downloadDateTime;
    private String localPath;
    private int fileSize;

    public PhotoResponseDTO() {
    }

    public PhotoResponseDTO(int albumId, int id, String title, String url, String thumbnailUrl, String downloadDateTime, String localPath, int fileSize) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoResponseDTO that = (PhotoResponseDTO) o;
        return albumId == that.albumId &&
                id == that.id &&
                fileSize == that.fileSize &&
                Objects.equals(title, that.title) &&
                Objects.equals(url, that.url) &&
                Objects.equals(thumbnailUrl, that.thumbnailUrl) &&
                Objects.equals(downloadDateTime, that.downloadDateTime) &&
                Objects.equals(localPath, that.localPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, id, title, url, thumbnailUrl, downloadDateTime, localPath, fileSize);
    }

    //Builder
    public static PhotoResponseDTO.Builder builder() {
        return new PhotoResponseDTO().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder albumId(int albumId) {
            PhotoResponseDTO.this.albumId = albumId;
            return this;
        }

        public Builder id(int id){
            PhotoResponseDTO.this.id = id;
            return this;
        }

        public Builder title(String title){
            PhotoResponseDTO.this.title = title;
            return this;
        }

        public Builder url(String path){
            PhotoResponseDTO.this.url = path;
            return this;
        }

        public Builder thumbnailUrl(String path){
            PhotoResponseDTO.this.thumbnailUrl = path;
            return this;
        }

        public Builder downloadDateTime(String time){
            PhotoResponseDTO.this.downloadDateTime = time;
            return this;
        }

        public Builder localPath(String path){
            PhotoResponseDTO.this.localPath = path;
            return this;
        }

        public Builder fileSize(int size){
            PhotoResponseDTO.this.fileSize = size;
            return this;
        }

        public PhotoResponseDTO build() {
            return PhotoResponseDTO.this;
        }

    }
}