package Spotify.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "album")
@EqualsAndHashCode(callSuper = false)
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
    private double duration;

    @Column(name= "year_release")
    private int yearRelease;

    @OneToMany(mappedBy = "album_ref", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongEntity> songs;

}
