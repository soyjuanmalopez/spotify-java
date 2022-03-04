package Spotify.persistence.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "album")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @Fetch(value = FetchMode.SUBSELECT)//orphanRemoval = true
    @JoinTable(
            name = "rel_album_artist",
            joinColumns = {@JoinColumn(name = "id_album")},
            inverseJoinColumns = {@JoinColumn(name = "id_artist")}
    )
    private List<ArtistEntity> artists;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "album_ref", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SongEntity> songs;

}
