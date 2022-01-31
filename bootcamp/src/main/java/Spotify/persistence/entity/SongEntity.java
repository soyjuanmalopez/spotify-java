package Spotify.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Null;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "song")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_song_artist",
            joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_artist")}
    )
    private List<ArtistEntity> artists;

    @ManyToMany(mappedBy = "songs")
    private Set<GenreEntity> genres;

}

