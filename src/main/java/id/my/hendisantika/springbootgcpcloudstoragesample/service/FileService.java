package id.my.hendisantika.springbootgcpcloudstoragesample.service;

import com.google.api.services.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
