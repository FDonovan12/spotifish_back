package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.interfaces.ImageInterface;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
public class FileManagerService {

    private final String UPLOAD_DIR = "public/";

    public void rename(String oldName, String newName) {
        Path oldPath = Paths.get(UPLOAD_DIR + oldName);
        Path newPath = Paths.get(UPLOAD_DIR + newName);

        if (!Files.exists(oldPath)) {
            throw new NotFoundSpotifishException("UploadService - rename("+oldName+")", "uploadSong", oldName);
        }

        try {
            Files.move(oldPath, newPath);
        } catch (IOException e) {
        }

    }
}