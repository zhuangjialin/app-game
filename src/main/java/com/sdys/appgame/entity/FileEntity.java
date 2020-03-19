package com.sdys.appgame.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class FileEntity {

    //注解，easypoi教程里有，时间转换、数据字典转换等
    @Excel(name = "姓名", orderNum = "0")
    public String fileId;
    @Excel(name = "性别", replace = {"男_1", "女_2"}, orderNum = "1")
    public String fileName;
    @Excel(name = "孙权", orderNum = "2")
    public String tags;
    /*如果不注释，抛异常，java.lang.InstantiationException（对象创建失败）
        public FileEntity(String fileId, String fileName, String tags) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.tags = tags;
        }
    */
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }


    @Override
    public String toString() {
        return "FileEntity{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
