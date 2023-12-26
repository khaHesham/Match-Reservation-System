package com.premierleague.reservation.api.controllers;

import com.premierleague.reservation.api.dtos.UserDTO;
import com.premierleague.reservation.api.exceptions.UnauthorizedException;
import com.premierleague.reservation.api.mappers.UserMapper;
import com.premierleague.reservation.api.models.User;
import com.premierleague.reservation.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{username}")
    public ResponseEntity<UserDTO> userProfile(@PathVariable String username) {
        try {
            return new ResponseEntity<>((userService.userProfile(username)), HttpStatus.OK);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // TODO: add admin role check
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try {
            // need to check if the user performing this action is an admin
            // else throw unauthorized exception
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }  catch (UnauthorizedException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Un Authorized",HttpStatus.UNAUTHORIZED);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approveUser(@PathVariable("id") Long id) {
        try {
            // need to check if the user performing this action is an admin
            // else throw unauthorized exception
            userService.approveUser(id);
            return new ResponseEntity<>("User approved successfully", HttpStatus.OK);
        }  catch (UnauthorizedException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("un Authorized ",HttpStatus.UNAUTHORIZED);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/request")
    public ResponseEntity<String> requestManager() {
        try {
            // need to check if the user performing this action is an admin
            // else throw unauthorized exception
            userService.requestManager();
            return new ResponseEntity<>("User requested to become manager", HttpStatus.OK);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
