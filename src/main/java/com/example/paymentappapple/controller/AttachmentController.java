package com.example.paymentappapple.controller;

import com.example.paymentappapple.entity.Attachment;
import com.example.paymentappapple.repository.AttachmentRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    private static final String uploadDirectory = "files";

    final
    AttachmentRepository attachmentRepository;

    public AttachmentController(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }


    @PostMapping("/uploadSystem")
    public boolean saveMultiple(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {

            MultipartFile file = request.getFile(fileNames.next());

            if (file != null) {

                UUID uuid = UUID.randomUUID();
                String substring = uuid.toString().substring(0, 5);
                String originalFilename = substring + "2022" + file.getOriginalFilename();
                Attachment attachment = new Attachment();
                attachment.setOriginalName(originalFilename);
                attachment.setSize(file.getSize());
                attachment.setType(file.getContentType());

                attachmentRepository.save(attachment);
                Path path = Paths.get(uploadDirectory + "/" + originalFilename);
                Files.copy(file.getInputStream(), path);
            } else {
                return false;
            }
        }
        return true;
    }

    @GetMapping("/downloadFromSystem/{id}")
    public void getOne(@PathVariable Integer id, HttpServletResponse response) throws IOException {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getOriginalName() + "\"");

            response.setContentType(attachment.getType());
            FileInputStream fileInputStream = new FileInputStream(uploadDirectory + "/" + attachment.getOriginalName());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }

    @GetMapping("/info/{id}")
    public ApiResponse getOne(@PathVariable Integer id) {
        Optional<Attachment> byId = attachmentRepository.findById(id);
        return byId.map(attachment -> new ApiResponse("FOUND", true, attachment)).orElseGet(() -> new ApiResponse("NOT FOUND", false));
    }

    @GetMapping("/info")
    public ApiResponse getAll() {
        return new ApiResponse("FOUND", true, attachmentRepository.findAll());
    }
}