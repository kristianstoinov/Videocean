package com.example.classes;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {
 
    MultipartFile file;
    String name;
    String description;
     
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}