package id.my.hendisantika.springbootgcpcloudstoragesample.service;

import com.google.api.gax.paging.Page;
import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Blob;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-gcp-cloud-storage-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 23/02/25
 * Time: 07.04
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class FileService {
    private final Storage storage;

    @Value("${gcp.bucket.name}")
    private String bucketName;

    public List<String> listOfFiles() {
        List<String> list = new ArrayList<>();
        Page<Blob> blobs = storage.list(bucketName);
        for (Blob blob : blobs.iterateAll()) {
            list.add(blob.getName());
        }
        return list;
    }

    public ByteArrayResource downloadFile(String fileName) {
        Blob blob = storage.get(bucketName, fileName);
        ByteArrayResource resource = new ByteArrayResource(
                blob.getContent());

        return resource;
    }
}
