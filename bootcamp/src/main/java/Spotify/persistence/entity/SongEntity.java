package Spotify.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Null;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.Nullable;

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
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_ref")
    private AlbumEntity album_ref;


    @Column(name = "reproductions")
    private Integer reproductions;


    @Column(name = "duration")
    private Double duration;

    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany()
    @JoinTable(
            name = "rel_song_artist",
            joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_artist")}
    )
    private List<ArtistEntity> artists;


    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(mappedBy = "songs")
    private Set<GenreEntity> genres;

}

