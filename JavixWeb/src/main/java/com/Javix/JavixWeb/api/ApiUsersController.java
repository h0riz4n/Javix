package com.Javix.JavixWeb.api;

import com.Javix.JavixWeb.javix.BotConfig;
import com.Javix.JavixWeb.models.Player;
import com.Javix.JavixWeb.repo.PlayerRepo;
import com.Javix.JavixWeb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.NonNull;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ApiUsersController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private BotConfig botCfg;

    @PostMapping("/myProfile")
    public ResponseEntity<Map> myProfile(@RequestParam(value = "id", defaultValue = "1") @NonNull long id, @RequestParam(value = "TOKEN") @NonNull String token) {
        if (botCfg.getTOKEN().equals(token)) {
            if (playerRepo.existsByTgId(id)) {
                Player player = playerRepo.getByTgId(id);
                return new ResponseEntity<>(playerService.getPlayer(player), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam(value = "id", defaultValue = "1") @NonNull long id, @RequestParam(value = "TOKEN") @NonNull String token) {
        if (botCfg.getTOKEN().equals(token)) {
            if (playerService.deleteUser(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/isUser")
    public ResponseEntity<HttpStatus> isUser(@RequestParam(value = "id", defaultValue = "0") @NonNull long id) {
        if (playerRepo.existsByTgId(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
