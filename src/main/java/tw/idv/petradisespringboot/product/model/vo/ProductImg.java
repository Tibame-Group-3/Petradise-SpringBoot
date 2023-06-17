package tw.idv.petradisespringboot.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_img")
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pd_img_id")
    Integer pdImgId;
    @Column(name = "pd_id")
    Integer pdId;
    @Lob
    @Column(name = "pd_img")
    byte[] pdImg;

}
