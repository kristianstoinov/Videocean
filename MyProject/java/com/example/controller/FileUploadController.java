package com.example.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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

import com.example.DAO.ClipDAO;
import com.example.DAO.PlaylistDAO;
import com.example.DAO.UserDAO;
import com.example.classes.Clip;
import com.example.classes.FileBucket;
import com.example.classes.FileValidator;
import com.example.classes.Playlist;
import com.example.classes.TYPE;
import com.example.classes.User;
import com.example.exceptions.ClipException;
import com.example.exceptions.PlaylistException;
import com.example.exceptions.UserProblemException;

 
@Controller
public class FileUploadController {
 
    private static String UPLOAD_LOCATION="C:/Users/traqn/Desktop/ITTALENTS/MyProject/src/main/webapp/static/videos/";
 
    
    
    
    @Autowired
    FileValidator fileValidator;
 
 
    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }
 
    @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model,HttpServletRequest request) {
    	
    	if(request.getSession().getAttribute("user") == null) {
    		return "error";
    		}
    	
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "upload";
    }
 
    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket,
            BindingResult result, ModelMap model,HttpServletRequest request ) throws IOException {
 
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "upload";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();
            User user=(User) request.getSession().getAttribute("user");
            UserDAO usDAO=new UserDAO();
            PlaylistDAO plDAO=new PlaylistDAO();
            Playlist playlist;
            int playlistId;
            int clipId;
            Clip clip;
            String path = null;
            System.out.println(fileBucket.getFile().getOriginalFilename());
            for(int count=fileBucket.getFile().getOriginalFilename().length()-1;count>0;count--){
            	if(fileBucket.getFile().getOriginalFilename().charAt(count)=='\\'){
            		path=fileBucket.getFile().getOriginalFilename().substring(count+1, fileBucket.getFile().getOriginalFilename().length());
            		break;
            	}
            }
            System.out.println(fileBucket.getName());
             try {
            	 if(path!=null){
            	 clip=new Clip(fileBucket.getName(), user, "videos/"+path, TYPE.PUBLIC);
            	 }
            	 else{
            		 clip=new Clip(fileBucket.getName(), user,"videos/"+fileBucket.getFile().getOriginalFilename(), TYPE.PUBLIC);
            	 }
            	 clipId=new ClipDAO().addClip(clip);
            	 clip.setClipID(clipId);
            	 playlist=new Playlist("My Clips", user, TYPE.PUBLIC);
				 playlistId=plDAO.createPlaylist(playlist);
				 usDAO.addPlaylistIntoLibrary(playlistId, user.getUserID());
				 plDAO.addClipToPlaylist(playlistId, clipId);
//				 user.addClipToMyClips(clip);
//				 user.getMyClips().setPlaylistID(playlistId);
				 request.setAttribute("user", user);
			} catch (PlaylistException | UserProblemException | ClipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
}
            // Now do something with file...
             System.out.println("do tuk e dobre");
            if(path!=null){
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( UPLOAD_LOCATION+path));
             }
             else{
            	 FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( UPLOAD_LOCATION+fileBucket.getFile().getOriginalFilename())); 
             }
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            return "redirect:index";
        }
    }
 
}