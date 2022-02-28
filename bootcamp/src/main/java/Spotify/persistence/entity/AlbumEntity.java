package Spotify.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlbumEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private Double duration;

    @Column(name= "year_release")
    private Integer yearRelease;

    @OneToMany(mappedBy = "album_ref")
    private Set<SongEntity> songs;

}
