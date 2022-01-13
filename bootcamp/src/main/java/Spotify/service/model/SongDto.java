package Spotify.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDto implements Serializable {

    private static final long serialVersionUID = 3098784982085101512L;

    private Long id;

    private String title;

    private int reproductions;

    private int album_ref;

    private double duration;

}