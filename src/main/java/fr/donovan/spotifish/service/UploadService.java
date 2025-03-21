package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Permission;
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
        System.out.println("UploadService.uploadImage");
        LikeableItem likeableItem = this.likeableItemService.getObjectBySlug(slug);
        if (!ImageInterface.class.isAssignableFrom(likeableItem.getClass())) {
            throw new NotFoundSpotifishException("UploadService - uploadImage("+slug+")", "uploadImage", slug);
        }
        ImageInterface imageInterface = (ImageInterface) likeableItem;
        System.out.println("assignable");
        System.out.println(imageInterface.getImage());
        return likeableItem;
    }

    public Song uploadSong(MultipartFile file, String slug) {
        Song song = this.songService.getObjectBySlug(slug);
        Path path = Paths.get(UPLOAD_DIR + song.getPath());
        System.out.println("path.toAbsolutePath() = " + path.toAbsolutePath());
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            File file2 = path.toFile();
            System.out.println("file2.getAbsolutePath() = " + file2.getAbsolutePath());
        } catch (Exception e) {
            throw new NotFoundSpotifishException("UploadService - uploadSong("+slug+")", "uploadSong", slug);
        }
        return song;
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