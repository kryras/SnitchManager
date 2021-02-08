package com.snitch.api.schedulers.helpers;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snitch.api.service.IUserService;
import com.snitch.entities.model.Snitch;
import com.snitch.entities.model.User;

import enums.ERole;
import javassist.NotFoundException;

@Component
public class MonthlyWinSchedulerHelper {

    @Autowired
    private IUserService userService;

    public Map<User, Integer> getThreeBestUsersFromPreviousMonthWithPointsMap() throws NotFoundException {
        List<User> users = userService.getUsersWithRole(ERole.ROLE_USER);
        Map<User, Integer> bestUsersWithPointsMap = new HashMap<>();

        for (User user : users) {
            int userPoints = user.getSnitchList().stream()
                    .filter(this::isSnitchFromPrevoiusMonth)
                    .mapToInt(Snitch::getSnitchPoints)
                    .sum();
            if (userPoints != 0) {
                bestUsersWithPointsMap.put(user, userPoints);
            }
        }

        return bestUsersWithPointsMap.size() < 3 ? bestUsersWithPointsMap : getThreeBestFromMap(bestUsersWithPointsMap);
    }

    private boolean isSnitchFromPrevoiusMonth(Snitch snitch) {
        Month previousMonth = LocalDate.now().minusMonths(1).getMonth();
        LocalDate snitchDate = snitch.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return previousMonth.equals(snitchDate.getMonth());
    }

    private Map<User, Integer> getThreeBestFromMap(Map<User, Integer> bestUsersWithPointsMap) {
        return bestUsersWithPointsMap.entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .limit(3)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }
}
