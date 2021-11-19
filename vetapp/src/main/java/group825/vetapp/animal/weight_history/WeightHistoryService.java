package group825.vetapp.animal.weight_history;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class WeightHistoryService {

    private final WeightHistoryRepo repo;

    public WeightHistoryService(WeightHistoryRepo repo) {
        this.repo = repo;
    }

    public List<WeightHistory> selectWeightHistory() {
        return this.repo.selectWeightHistory();
    }

    public int addWeightHistory(WeightHistory weightHistory) {
        return this.repo.addWeightHistory(weightHistory);
    }

    public int editWeightHistory(UUID id, LocalDate date, double weight) {
        return this.repo.editWeightHistory(id, date, weight);
    }

    public int deleteWeightHistory(UUID id) {
        return this.repo.deleteWeightHistory(id);
    }




}
