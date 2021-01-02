package com.ptv.escort.Response;

import com.ptv.escort.Admin.EscortDetails;

public class ImageFileResponse {


    private String fileDownloadUri;
    private String fileType;
    private long size;
    private EscortDetails escortDetails;




    public ImageFileResponse(String fileDownloadUri, String fileType, long size, EscortDetails escortDetails) {
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.escortDetails = escortDetails;
    }


    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public EscortDetails getEscortDetails() {
        return escortDetails;
    }

    public void setEscortDetails(EscortDetails escortDetails) {
        this.escortDetails = escortDetails;
    }
}
