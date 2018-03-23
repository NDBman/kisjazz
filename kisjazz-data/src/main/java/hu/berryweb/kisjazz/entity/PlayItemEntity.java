package hu.berryweb.kisjazz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Nandi on 2018. 03. 13..
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PLAY_ITEM")
public class PlayItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPOITY_ID")
    private String spoitfyId;

    @Column(name = "REQUEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @Column(name = "PLAYING")
    private Boolean playing;
}
