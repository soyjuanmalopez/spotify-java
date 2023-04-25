package spotify.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_ref")
    private AlbumEntity album;


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
    private Set<GenereEntity> generes;

}

