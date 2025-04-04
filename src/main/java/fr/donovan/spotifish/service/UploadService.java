package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Permission;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.interfaces.ImageInterface;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UploadService {

    private LikeableItemService likeableItemService;
    private SongService songService;

    private final String UPLOAD_DIR = "public/";

    public LikeableItem uploadImage(MultipartFile file, String slug) {
        LikeableItem likeableItem = this.likeableItemService.getObjectBySlug(slug);
        if (!ImageInterface.class.isAssignableFrom(likeableItem.getClass())) {
            throw new NotFoundSpotifishException("UploadService - uploadImage("+slug+")", "uploadImage", slug);
        }
        ImageInterface imageInterface = (ImageInterface) likeableItem;
        return likeableItem;
    }

    public Song uploadSong(MultipartFile file, String slug) {
        Song song = this.songService.getObjectBySlug(slug);
        Path path = Paths.get(UPLOAD_DIR + song.getPath());
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            File file2 = path.toFile();
        } catch (Exception e) {
            throw new NotFoundSpotifishException("UploadService - uploadSong("+slug+")", "uploadSong", slug);
        }
        return song;
    }

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

    private long getAudioDuration(File file) throws Exception {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        AudioFormat format = audioInputStream.getFormat();
        long audioFileLength = file.length();
        int frameSize = format.getFrameSize();
        float frameRate = format.getFrameRate();
        return (long) (audioFileLength / (frameSize * frameRate));
    }
}