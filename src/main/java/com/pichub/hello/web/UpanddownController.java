package com.pichub.hello.web;

import com.pichub.hello.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by root on 19-3-24.
 */
@Controller
public class UpanddownController {
    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "/uploadFile" ,method = RequestMethod.POST)
    public String uploadFile(@RequestParam(value="file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        if(file == null && file.getSize() > 0)
        {
            return "{" + "false" + "}";
        }


        String fileName = file.getOriginalFilename();
        String exName = fileName.substring(fileName.lastIndexOf(".") + 1 );
        String newOriginiName = UUID.randomUUID().toString().replaceAll("-","") + "." + exName;
        String originPicturePath = getParent(request.getServletContext().getRealPath("/"))
                             + "resources" + File.separator + "originPictures" +File.separator;

        FileOutputStream fos;//upload origin picture
        try {
            fos = new FileOutputStream(originPicturePath + File.separator + newOriginiName);
            fos.write(file.getBytes());
            fos.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //upload thumbnail
        String newThumbnailName =UUID.randomUUID().toString().replaceAll("-", "") + "." + exName;
        String thumnailPath = getParent(request.getServletContext().getRealPath(","))
                            + "resources" + File.separator + "static" + File.separator + "thumbnail" + File.separator;


        return "{" + "true" + "}";

    }


    private String getParent(String realPath)
    {
        String str = realPath.substring(0,realPath.lastIndexOf(File.separator));
        return str.substring(0,str.lastIndexOf(File.separator) + 1);
    }


}