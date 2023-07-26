package fileupload;

import com.fasterxml.jackson.annotation.JsonAnyGetter;


public class FileDTO {

    //private : 클래스 내부에서만 사용가능
    //
    private String originFileName;
    private String saveName;
    private String filePath;
    private String FileDescription;

    public FileDTO() {
    }

    public FileDTO(String originFileName, String saveName, String filePath, String fileDescription) {
        this.originFileName = originFileName;
        this.saveName = saveName;
        this.filePath = filePath;
        FileDescription = fileDescription;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }
    //객체 무결성을 보장하기 위해서
    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return FileDescription;
    }

    public void setFileDescription(String fileDescription) {
        FileDescription = fileDescription;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "originFileName='" + originFileName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", FileDescription='" + FileDescription + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
