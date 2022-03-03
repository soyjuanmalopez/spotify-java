package Spotify.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "artist")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArtistEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "artists",fetch = FetchType.LAZY)
    private Set<SongEntity> songs;

    @ManyToMany(mappedBy = "artists")
    private List<AlbumEntity> albums;
}
