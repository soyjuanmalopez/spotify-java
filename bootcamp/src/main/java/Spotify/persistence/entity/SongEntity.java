package Spotify.persistence.entity;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "song")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SongEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_ref")
    private AlbumEntity album_ref;

    @Column(name = "reproductions")
    private int reproductions;

    @Column(name = "duration")
    private double duration;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "rel_song_artist",
            joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_artist")}
    )
    private List<ArtistEntity> artists;

    @ManyToMany(mappedBy = "songs")
    private List<GenreEntity> genres;


}

