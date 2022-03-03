package Spotify.persistence.entity;

import lombok.*;

import javax.management.ConstructorParameters;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genre")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GenreEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_genre_songs",
            joinColumns = {@JoinColumn(name = "id_genre")},
            inverseJoinColumns = {@JoinColumn(name = "id_song")}
    )
    private Set<SongEntity> songs;
}
