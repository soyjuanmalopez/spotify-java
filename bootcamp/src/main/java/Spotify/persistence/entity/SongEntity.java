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
@Data
public class SongEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "album_ref")
    private int album_ref;

    @Column(name = "reproductions")
    private int reproductions;

    @Column(name = "duration")
    private double duration;

    @ManyToMany(mappedBy = "songs")
    private List<ArtistEntity> artists;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "song_genre",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<GenreEntity> genres;


}

