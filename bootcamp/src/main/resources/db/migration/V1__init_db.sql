CREATE TABLE `spotify`.`album`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(250) NULL,
    `duration`     DOUBLE       NULL,
    `year_release` INT          NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `spotify`.`song`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `title`         VARCHAR(250) NULL,
    `duration`      DOUBLE       NULL,
    `reproductions` INT          NULL,
    `album_ref`     INT          NULL,
    PRIMARY KEY (`id`),
    INDEX `album_fk_idx` (`album_ref` ASC) VISIBLE,
    CONSTRAINT `album_fk`
        FOREIGN KEY (`album_ref`)
            REFERENCES `spotify`.`album` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
CREATE TABLE `spotify`.`artist`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(250) NULL,
    `description` VARCHAR(800) NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `spotify`.`genre`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(90) NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `spotify`.`rel_genre_songs`
(
    `id_genre` INT NOT NULL,
    `id_song`  INT NOT NULL,
    PRIMARY KEY (`id_genre`, `id_song`),
    INDEX `fk_rel_song_idx` (`id_song` ASC) VISIBLE,
    CONSTRAINT `fk_rel_genre`
        FOREIGN KEY (`id_genre`)
            REFERENCES `spotify`.`genre` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_rel_song`
        FOREIGN KEY (`id_song`)
            REFERENCES `spotify`.`song` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
CREATE TABLE `spotify`.`rel_album_artist`
(
    `id_album`  INT NOT NULL,
    `id_artist` INT NOT NULL,
    PRIMARY KEY (`id_album`, `id_artist`),
    INDEX `fk_rel_albumartist_artist_idx` (`id_artist` ASC) VISIBLE,
    CONSTRAINT `fk_rel_albumartist_album`
        FOREIGN KEY (`id_album`)
            REFERENCES `spotify`.`album` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_rel_albumartist_artist`
        FOREIGN KEY (`id_artist`)
            REFERENCES `spotify`.`artist` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
CREATE TABLE `spotify`.`rel_song_artist`
(
    `id_song`   INT NOT NULL,
    `id_artist` INT NOT NULL,
    PRIMARY KEY (`id_song`, `id_artist`),
    INDEX `fk_rel_songartist_artist_idx` (`id_artist` ASC) VISIBLE,
    CONSTRAINT `fk_rel_songartist_song`
        FOREIGN KEY (`id_song`)
            REFERENCES `spotify`.`song` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_rel_songartist_artist`
        FOREIGN KEY (`id_artist`)
            REFERENCES `spotify`.`artist` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);


INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Drake',
        'Canadian rapper and vocalist Drake sustained a high-level commercial presence shortly after he hit the scene in 2006, whether with his own chart-topping releases or a long string of guest appearances on hits by the likes of Lil Wayne, Rihanna , and A$AP Rocky .');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Giggs',
        'Giggs is a British rapper who made his critically acclaimed solo album debut in 2008 with Walk in da Park.');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Black Coffee',
        'South African house music DJ and producer Black Coffee built his career gradually over nearly two decades, riding the growing global interest in his home country\'s burgeoning dance music scene and eventually becoming arguably the biggest DJ in Africa.');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Jorja Smith',
        'Jorja Smith is an English R & B singer/songwriter whose soulful, jazz-tinged cadence, heartfelt lyrics, and retro sound invoke names like Lauryn Hill, Alicia Keys, Rihanna, and Amy Winehouse, the latter of whom the Walsall-based artist cites as her biggest influence.');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Sampha',
        'In February 2017, Sampha released his debut album Process via Young Turks. The album - an achingly beautiful, emotionally raw and musically adventurous body of work co-produced by Sampha and Rodaidh McDonald - was the culmination of years of work for the singer, songwriter and producer who hails from South London. ');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Quavo',
        'Primarily known for being one-third of the hip-hop/trap collective Migos, Quavo (real name Quavious Marshall) is a rapper and hip-hop artist from Lawrenceville, Georgia.');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Travis Scott',
        'Travis Scott became known during the early 2010s for his heavily Auto-Tuned half-sung/half-rapped vocal style.');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('2 Chainz',
        'I am the Drench God aka Tity Boi aka Soufside\'s own Hairweavekiller, but yall know me as 2 Chainz!!');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Young Thug',
        'Flouting hip-hop conventions while defying gender and sexuality stereotypes, Young Thug has been one of the most distinctive contemporary rap artists since attaining his chart debut with \"Stoner\" (2014).');
INSERT INTO `spotify`.`artist` (`name`, `description`)
VALUES ('Kanye West',
        'One of the most influential and critically lauded artists of the early 21st century, Kanye West went from hip-hop beatmaker to worldwide hitmaker as his production work for artists such as Jay-Z  led to a major-label recording contract');
INSERT INTO `spotify`.`artist` (`name`)
VALUES ('PARTYNEXTDOOR');

INSERT INTO `spotify`.`album` (`title`, `duration`, `year_release`)
VALUES ('More Life', '1.21', '2017');
INSERT INTO `spotify`.`rel_album_artist` (`id_album`, `id_artist`)
VALUES ('1', '1');

INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Free smoke', '3.38', '170300000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('No Long Talk', '2.29', '116090000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Passionfruit', '4.58', '1009000000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Jorja Interlude', '1.47', '102042000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Get It Together', '4.10', '192403000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Madiba Riddim', '3.25', '192403000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Blem', '3.25', '129680000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('4422', '3.06', '120230000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Gyalchester', '3.09', '306130000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Skepta Interlude', '2.23', '75900000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Portland', '3.56', '468400000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Sacrifices', '5.07', '128530000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Nothings Into Somethings', '2.33', '75730000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Teenage Fever', '3.39', '352195000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('KMT', '2.42', '145600000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Lose You', '5.05', '70350000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Can\'t Have Everything', '3.48', '83960000', '1');

INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Glow', '3.26', '70028000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Since Way Back', '6.08', '68760311', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Fake Love', '3.30', '818115000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Ice Melts', '4.10', '103700000', '1');
INSERT INTO `spotify`.`song` (`title`, `duration`, `reproductions`, `album_ref`)
VALUES ('Do Not Disturb', '4.43', '260410000', '1');

INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('1', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('2', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('2', '2');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('3', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('4', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('5', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('5', '3');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('5', '4');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('6', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('7', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('8', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('8', '5');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('9', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('10', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('11', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('11', '6');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('11', '7');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('12', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('12', '8');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('12', '9');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('13', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('14', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('15', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('15', '2');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('16', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('17', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('18', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('18', '10');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('19', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('19', '11');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('20', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('21', '1');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('21', '9');
INSERT INTO `spotify`.`rel_song_artist` (`id_song`, `id_artist`)
VALUES ('22', '1');

INSERT INTO `spotify`.`genre` (`name`)
VALUES ('Hip-Hop');

INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '1');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '2');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '3');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '4');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '5');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '6');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '7');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '8');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '9');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '10');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '11');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '12');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '13');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '14');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '15');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '16');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '17');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '18');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '19');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '20');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '21');
INSERT INTO `spotify`.`rel_genre_songs` (`id_genre`, `id_song`)
VALUES ('1', '22');

