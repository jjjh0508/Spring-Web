package fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile, String singleFileDescription, Model model) {
        System.out.println("singleFile : " + singleFile);
        System.out.println("singleFileDescription : " + singleFileDescription);


        /*
         * 파일을 저장할 경로
         * */
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadFiles";
//        String root = "src/main/resources/static";
//        String filePath = root + "/uploadFiles";

        File dir = new File(path);
        System.out.println(dir.getAbsolutePath());

        if (!dir.exists()) {
            dir.mkdir();
        }

        String originFileName = singleFile.getOriginalFilename(); // 현재 파일의 이름을 가져옴
        String ext = originFileName.substring(originFileName.lastIndexOf("."));// 확장자를 가져옴
        String saveName = UUID.randomUUID().toString().replace("-", "") + ext;

        try {
            singleFile.transferTo(new File(path + "/" + saveName));
            model.addAttribute("message", "파일 업로드 성공");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "파일 업로드 실패");
        }
        return "result";

    }

    @PostMapping("multi-file")
    public String multiFiles(@RequestParam(value = "multiFile") List<MultipartFile> files, String multiFileDescription, Model model) {
        System.out.println("multiFiles : " + files);
        System.out.println("multiFileDescription" + multiFileDescription);

        //파일 저장할 경로 설정
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadFiles";
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        List<FileDTO> multiFiles = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String originFileName = file.getOriginalFilename(); // 현재 파일의 이름을 가져옴
                String ext = originFileName.substring(originFileName.lastIndexOf("."));// 확장자를 가져옴
                String saveName = UUID.randomUUID().toString().replace("-", "") + ext;

                multiFiles.add(new FileDTO(originFileName, saveName, path, multiFileDescription));
                System.out.println(path+"/"+saveName);

                file.transferTo(new File(path + "/" + saveName));
            }
            model.addAttribute("message", "멀티 파일 저장됨");
        } catch (IOException e) {
            e.printStackTrace();
            for (FileDTO file: multiFiles) {
                new File(path+"/"+file.getSaveName()).delete();

            }
            model.addAttribute("message", "실패됨");
        }
        return "result";
    }
}
