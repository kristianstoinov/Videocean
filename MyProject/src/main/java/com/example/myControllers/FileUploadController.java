package com.example.myControllers;

import java.io.File;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
 
import com.example.classes.FileBucket;
import com.example.classes.FileValidator;

 
@Controller
public class FileUploadController {
 
    private static String UPLOAD_LOCATION="D:/VideoceanFiles/";
 
    @Autowired
    FileValidator fileValidator;
 
 
    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }
 
    @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "upload";
    }
 
    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket,
            BindingResult result, ModelMap model) throws IOException {
 
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "upload";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();
 
            // Now do something with file...
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            return "success";
        }
    }
 
}