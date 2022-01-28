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
@Data
@ToString
public class AlbumEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private double duration;

    @Column(name= "year_release")
    private int yearRelease;

    @OneToMany(mappedBy = "album_ref", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SongEntity> songs;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "rel_album_artist",
            joinColumns = @JoinColumn(name = "id_album", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_artist", nullable = false)
    )
    private List<ArtistEntity> artists;

}
