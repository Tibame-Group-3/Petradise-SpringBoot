package tw.idv.petradisespringboot.roomorder.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RoomOrderScheduler {

    private final RoomOrderService roomOrderService;

    public RoomOrderScheduler(RoomOrderService roomOrderService) {
        this.roomOrderService = roomOrderService;
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs at midnight every day
    public void updateStatusOfUpcomingOrders() {
        roomOrderService.updateStatusOfUpcomingOrders();
    }
}
