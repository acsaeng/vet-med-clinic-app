package group825.vetapp.animal.weight_history;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("tempWeightHistoryRepo")
public class WeightHistoryRepo {

    private static final List<WeightHistory> database = new ArrayList<>();

    public List<WeightHistory> selectWeightHistory() {
        return database;
    }

    public int addWeightHistory(WeightHistory weightHistory) {
        database.add(weightHistory);
        return 1;
    }

    public int editWeightHistory(UUID id, LocalDate date, double weight) {
        return selectWeightHistoryById(id).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setDate(date);
                database.get(accountIdx).setWeight(weight);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    public int deleteWeightHistory(UUID id) {
        return selectWeightHistoryById(id).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.remove(accountIdx);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    private Optional<WeightHistory> selectWeightHistoryById(UUID id) {
        return database.stream().filter(weightHistory -> weightHistory.getId().equals(id)).findFirst();
    }
}
