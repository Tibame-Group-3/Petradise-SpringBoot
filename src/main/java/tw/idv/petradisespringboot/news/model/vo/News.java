package tw.idv.petradisespringboot.news.model.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
