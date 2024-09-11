package rentcar.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import rentcar.PointApplication;
import rentcar.domain.PointDecreased;
import rentcar.domain.PointIncreased;

@Entity
@Table(name = "Point_table")
@Data
//<<< DDD / Aggregate Root
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Integer point;

    @PostPersist
    public void onPostPersist() {
        PointIncreased pointIncreased = new PointIncreased(this);
        pointIncreased.publishAfterCommit();

        PointDecreased pointDecreased = new PointDecreased(this);
        pointDecreased.publishAfterCommit();
    }

    public static PointRepository repository() {
        PointRepository pointRepository = PointApplication.applicationContext.getBean(
            PointRepository.class
        );
        return pointRepository;
    }

    //<<< Clean Arch / Port Method
    public static void increasePoint(Reserved reserved) {
        //implement business logic here:

        repository().findByUserId(reserved.getUserId()).ifPresentOrElse(point -> {
            point.setPoint(point.getPoint() + 1);
            repository().save(point);

            PointIncreased pointIncreased = new PointIncreased(point);
            pointIncreased.publishAfterCommit();
        }, () -> {
            Point point = new Point();
            point.setUserId(reserved.getUserId());
            point.setPoint(1);
            repository().save(point);

            PointIncreased pointIncreased = new PointIncreased(point);
            pointIncreased.publishAfterCommit();
        });
        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        PointIncreased pointIncreased = new PointIncreased(point);
        pointIncreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reserved.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);

            PointIncreased pointIncreased = new PointIncreased(point);
            pointIncreased.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
