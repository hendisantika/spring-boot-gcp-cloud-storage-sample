package id.my.hendisantika.springbootgcpcloudstoragesample.controller;

import id.my.hendisantika.springbootgcpcloudstoragesample.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-gcp-cloud-storage-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 23/02/25
 * Time: 07.08
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileService fileService;

    //List all file name
    @GetMapping
    public ResponseEntity<List<String>> listOfFiles() {
        List<String> files = fileService.listOfFiles();

        return ResponseEntity.ok(files);
    }

    //Upload file
    @PostMapping("upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        fileService.uploadFile(file);

        return ResponseEntity.ok("File uploaded successfully");
    }

    //Delete file
    @DeleteMapping("delete")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        fileService.deleteFile(fileName);

        return ResponseEntity.ok(" File deleted successfully");
    }

    //Download file
    @GetMapping("download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        ByteArrayResource resource = fileService.downloadFile(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"");

        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_OCTET_STREAM).
                headers(headers).body(resource);
    }
}
