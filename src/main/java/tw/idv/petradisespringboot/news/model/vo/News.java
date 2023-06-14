package tw.idv.morgan.news.model.vo;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "news_list")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    Integer newsId;
    @Column(name = "admin_id")
    Integer adminId;
    @Column(name = "news_title")
    String newsTitle;
    @Column(name = "news_content")
    String newsContent;
    @Column(name = "news_date")
    Date newsDate;
    @Column(name = "news_photo", insertable = false)
    byte[] newsPhoto;

}
