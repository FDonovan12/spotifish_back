package fr.donovan.spotifish.service;

import com.mpatric.mp3agic.Mp3File;
import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Permission;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.interfaces.ImageInterface;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import fr.donovan.spotifish.repository.SongRepository;
import fr.donovan.spotifish.security.SecurityService;
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

    private final LikeableItemService likeableItemService;
    private final SongService songService;
    private final SongRepository songRepository;
    private final SecurityService securityService;

    private final String UPLOAD_DIR = "public/";

    public LikeableItem uploadImage(MultipartFile file, String slug) {
        LikeableItem likeableItem = this.likeableItemService.getObjectBySlug(slug);
        this.securityService.assertCanEdit(likeableItem);
        if (!ImageInterface.class.isAssignableFrom(likeableItem.getClass())) {
            throw new NotFoundSpotifishException("UploadService - uploadImage("+slug+")", "uploadImage", slug);
        }
        ImageInterface imageInterface = (ImageInterface) likeableItem;
        return likeableItem;
    }

    public Song uploadSong(MultipartFile file, String slug) {
        Song song = this.songService.getObjectBySlug(slug);
        System.out.println(song);
        this.securityService.assertCanEdit(song);
        System.out.println("UPLOAD_DIR");
        Path path = Paths.get(UPLOAD_DIR + song.getPath());
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            Mp3File mp3File = new Mp3File(path.toString());
            if (mp3File.hasId3v2Tag() || mp3File.hasId3v1Tag()) {
                int durationInSeconds = (int) mp3File.getLengthInSeconds();
                song.setDuration(durationInSeconds);
                this.songRepository.flush();
            }
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